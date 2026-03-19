package com.wariumapi.performance;

import com.wariumapi.WariumApiMod;
import com.wariumapi.ballistics.ImpactResult;
import com.wariumapi.event.MunitionDetonateEvent;
import com.wariumapi.event.ProjectileImpactEvent;
import com.wariumapi.event.VehicleControlInputEvent;
import com.wariumapi.vehicle.ControlFlag;
import com.wariumapi.vehicle.ControlState;
import com.wariumapi.vehicle.VehicleController;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WariumApiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PerformanceGuardHandlers {
    private static final Map<NodeKey, CachedControl> CONTROL_CACHE = new ConcurrentHashMap<>();
    private static final Map<ControllerKey, WindowCounter> CONTROL_WINDOW_COUNTERS = new ConcurrentHashMap<>();
    private static final Map<LevelTickKey, Integer> DETONATION_TICK_COUNTS = new ConcurrentHashMap<>();
    private static final Map<LevelTickKey, Integer> CONTROL_TICK_COUNTS = new ConcurrentHashMap<>();
    private static final Map<ClassLevelTickKey, Integer> CLASS_CONTROL_TICK_COUNTS = new ConcurrentHashMap<>();
    private static final Map<ControllerIdentityKey, IdleState> CONTROLLER_IDLE_STATES = new ConcurrentHashMap<>();

    private PerformanceGuardHandlers() {
    }

    @SubscribeEvent
    public static void onImpact(ProjectileImpactEvent event) {
        if (!PerformanceState.isDegraded()) {
            return;
        }

        ImpactResult result = event.getImpactResult();
        double scaled = result.damageMultiplier() * PerformanceGuardConfig.IMPACT_DAMAGE_MULTIPLIER_UNDER_LOAD.get();
        result.setDamageMultiplier(Math.max(0.0, scaled));
        PerformanceGuardTelemetry.recordImpactScaled();
    }

    @SubscribeEvent
    public static void onDetonate(MunitionDetonateEvent event) {
        Level detonationLevel = event.getLevel();
        LevelTickKey detonationKey = new LevelTickKey(detonationLevel.dimension(), detonationLevel.getGameTime());
        int detonationCount = DETONATION_TICK_COUNTS.merge(detonationKey, 1, Integer::sum);
        if (detonationCount > PerformanceGuardConfig.DETONATION_MAX_EVENTS_PER_LEVEL_PER_TICK.get()) {
            event.setCanceled(true);
            PerformanceGuardTelemetry.recordDetonationCanceledBudget();
            return;
        }

        PerformanceState.GuardMode mode = PerformanceState.getMode();
        if (mode == PerformanceState.GuardMode.DISABLED || mode == PerformanceState.GuardMode.NORMAL) {
            return;
        }

        double cancelChance = mode == PerformanceState.GuardMode.CRITICAL
                ? PerformanceGuardConfig.DETONATION_CANCEL_CHANCE_CRITICAL.get()
                : PerformanceGuardConfig.DETONATION_CANCEL_CHANCE_DEGRADED.get();
        if (mode == PerformanceState.GuardMode.CRITICAL
                && PerformanceGuardConfig.CANCEL_DETONATIONS_UNDER_CRITICAL_LOAD.get()) {
            cancelChance = 1.0;
        }

        if (cancelChance <= 0.0) {
            return;
        }

        double sample = event.getLevel().getRandom().nextDouble();
        if (sample <= cancelChance) {
            event.setCanceled(true);
            PerformanceGuardTelemetry.recordDetonationCanceled();
        }
    }

    @SubscribeEvent
    public static void onVehicleInput(VehicleControlInputEvent event) {
        if (!PerformanceState.isDegraded()) {
            return;
        }
        Level level = event.getLevel();
        if (level.isClientSide()) {
            return;
        }

        VehicleController controller = event.getNode().getVehicle().orElse(null);
        if (controller == null) {
            return;
        }

        LevelTickKey controlTickKey = new LevelTickKey(level.dimension(), level.getGameTime());
        int controlUpdates = CONTROL_TICK_COUNTS.merge(controlTickKey, 1, Integer::sum);
        if (controlUpdates > PerformanceGuardConfig.CONTROL_MAX_UPDATES_PER_LEVEL_PER_TICK.get()) {
            CachedControl existing = CONTROL_CACHE.get(new NodeKey(level.dimension(), event.getNodePos()));
            if (existing != null) {
                controller.setControlState(existing.state);
            }
            PerformanceGuardTelemetry.recordVehicleInputDroppedBudget();
            return;
        }

        long gameTime = level.getGameTime();
        NodeKey key = new NodeKey(level.dimension(), event.getNodePos());
        ControllerKey controllerKey = new ControllerKey(level.dimension(), event.getNode().getControllerId().orElse(null), event.getNodePos());
        ControllerIdentityKey controllerIdentityKey = new ControllerIdentityKey(level.dimension(), event.getNode().getControllerId().orElse(null), event.getNodePos());
        ControlState current = event.getState();
        VehicleClass vehicleClass = VehicleClassPolicy.resolve(event, current);

        if (isClassBudgetExceeded(level, vehicleClass)) {
            CachedControl existing = CONTROL_CACHE.get(key);
            if (existing != null) {
                controller.setControlState(existing.state);
            }
            PerformanceGuardTelemetry.recordVehicleInputDroppedClassBudget();
            return;
        }

        if (isIdleSleepSuppressed(controllerIdentityKey, gameTime, current)) {
            CachedControl existing = CONTROL_CACHE.get(key);
            if (existing != null) {
                controller.setControlState(existing.state);
            }
            PerformanceGuardTelemetry.recordVehicleInputSuppressedIdleSleep();
            return;
        }

        CachedControl cached = CONTROL_CACHE.get(key);

        if (cached == null) {
            if (isBackpressureExceeded(controllerKey, gameTime)) {
                PerformanceGuardTelemetry.recordVehicleInputDroppedBackpressure();
                return;
            }
            CONTROL_CACHE.put(key, new CachedControl(copy(current), gameTime));
            return;
        }

        double deadzone = PerformanceGuardConfig.CONTROL_INPUT_DEADZONE.get();
        int minInterval = PerformanceGuardConfig.CONTROL_INPUT_MIN_INTERVAL_TICKS.get();
        boolean smallChange = isSmallChange(cached.state, current, deadzone);
        boolean tooSoon = gameTime - cached.lastAppliedTick < minInterval;

        if (smallChange && tooSoon) {
            controller.setControlState(cached.state);
            PerformanceGuardTelemetry.recordVehicleInputSuppressed();
            return;
        }

        if (isBackpressureExceeded(controllerKey, gameTime)) {
            controller.setControlState(cached.state);
            PerformanceGuardTelemetry.recordVehicleInputDroppedBackpressure();
            return;
        }

        CONTROL_CACHE.put(key, new CachedControl(copy(current), gameTime));
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !PerformanceState.isEnabled()) {
            return;
        }
        Level level = event.level;
        if (level.isClientSide()) {
            return;
        }

        long gameTime = level.getGameTime();
        int ttl = PerformanceGuardConfig.CONTROL_CACHE_TTL_TICKS.get();

        Iterator<Map.Entry<NodeKey, CachedControl>> it = CONTROL_CACHE.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<NodeKey, CachedControl> entry = it.next();
            if (entry.getValue().lastAppliedTick + ttl < gameTime) {
                it.remove();
                PerformanceGuardTelemetry.recordCacheEntryEvicted();
            }
        }

        Iterator<Map.Entry<ControllerKey, WindowCounter>> counterIt = CONTROL_WINDOW_COUNTERS.entrySet().iterator();
        while (counterIt.hasNext()) {
            Map.Entry<ControllerKey, WindowCounter> entry = counterIt.next();
            if (entry.getValue().windowStartTick + ttl < gameTime) {
                counterIt.remove();
            }
        }

        Iterator<Map.Entry<LevelTickKey, Integer>> detonationTickIt = DETONATION_TICK_COUNTS.entrySet().iterator();
        while (detonationTickIt.hasNext()) {
            Map.Entry<LevelTickKey, Integer> entry = detonationTickIt.next();
            if (!entry.getKey().dimension.equals(level.dimension()) || entry.getKey().tick + 2L < gameTime) {
                detonationTickIt.remove();
            }
        }

        Iterator<Map.Entry<LevelTickKey, Integer>> controlTickIt = CONTROL_TICK_COUNTS.entrySet().iterator();
        while (controlTickIt.hasNext()) {
            Map.Entry<LevelTickKey, Integer> entry = controlTickIt.next();
            if (!entry.getKey().dimension.equals(level.dimension()) || entry.getKey().tick + 2L < gameTime) {
                controlTickIt.remove();
            }
        }

        Iterator<Map.Entry<ClassLevelTickKey, Integer>> classTickIt = CLASS_CONTROL_TICK_COUNTS.entrySet().iterator();
        while (classTickIt.hasNext()) {
            Map.Entry<ClassLevelTickKey, Integer> entry = classTickIt.next();
            if (!entry.getKey().dimension.equals(level.dimension()) || entry.getKey().tick + 2L < gameTime) {
                classTickIt.remove();
            }
        }

        Iterator<Map.Entry<ControllerIdentityKey, IdleState>> idleIt = CONTROLLER_IDLE_STATES.entrySet().iterator();
        while (idleIt.hasNext()) {
            Map.Entry<ControllerIdentityKey, IdleState> entry = idleIt.next();
            if (!entry.getKey().dimension.equals(level.dimension()) || entry.getValue().lastSeenTick + ttl < gameTime) {
                idleIt.remove();
            }
        }
    }

    private static boolean isClassBudgetExceeded(Level level, VehicleClass vehicleClass) {
        if (!PerformanceGuardConfig.VEHICLE_CLASS_BUDGETS_ENABLED.get()) {
            return false;
        }

        int budget = VehicleClassPolicy.perTickBudget(vehicleClass);
        ClassLevelTickKey classTickKey = new ClassLevelTickKey(level.dimension(), level.getGameTime(), vehicleClass);
        int classCount = CLASS_CONTROL_TICK_COUNTS.merge(classTickKey, 1, Integer::sum);
        return classCount > budget;
    }

    private static boolean isIdleSleepSuppressed(ControllerIdentityKey key, long gameTime, ControlState state) {
        if (!PerformanceGuardConfig.IDLE_SLEEP_ENABLED.get()) {
            return false;
        }

        double wakeThreshold = PerformanceGuardConfig.IDLE_WAKE_THRESHOLD.get();
        boolean nearZero = Math.abs(state.throttle()) <= wakeThreshold
                && Math.abs(state.yaw()) <= wakeThreshold
                && Math.abs(state.pitch()) <= wakeThreshold
                && Math.abs(state.roll()) <= wakeThreshold;

        IdleState current = CONTROLLER_IDLE_STATES.get(key);
        if (current == null) {
            CONTROLLER_IDLE_STATES.put(key, new IdleState(nearZero ? 1 : 0, gameTime, gameTime));
            return false;
        }

        int idleTicks = nearZero ? current.idleTicks + 1 : 0;
        IdleState next = new IdleState(idleTicks, current.lastPassThroughTick, gameTime);
        CONTROLLER_IDLE_STATES.put(key, next);

        if (!nearZero) {
            return false;
        }

        int idleAfter = PerformanceGuardConfig.IDLE_SLEEP_AFTER_TICKS.get();
        if (idleTicks < idleAfter) {
            return false;
        }

        int passThroughInterval = PerformanceGuardConfig.IDLE_SLEEP_PASS_THROUGH_INTERVAL_TICKS.get();
        if (gameTime - next.lastPassThroughTick >= passThroughInterval) {
            CONTROLLER_IDLE_STATES.put(key, new IdleState(idleTicks, gameTime, gameTime));
            return false;
        }

        return true;
    }

    private static boolean isBackpressureExceeded(ControllerKey key, long gameTime) {
        if (!PerformanceGuardConfig.CONTROL_BACKPRESSURE_ENABLED.get()) {
            return false;
        }

        int windowTicks = PerformanceGuardConfig.CONTROL_BACKPRESSURE_WINDOW_TICKS.get();
        int maxPerWindow = PerformanceGuardConfig.CONTROL_BACKPRESSURE_MAX_UPDATES_PER_WINDOW.get();

        WindowCounter counter = CONTROL_WINDOW_COUNTERS.get(key);
        if (counter == null || counter.windowStartTick + windowTicks <= gameTime) {
            CONTROL_WINDOW_COUNTERS.put(key, new WindowCounter(gameTime, 1));
            return false;
        }

        if (counter.count >= maxPerWindow) {
            return true;
        }

        CONTROL_WINDOW_COUNTERS.put(key, new WindowCounter(counter.windowStartTick, counter.count + 1));
        return false;
    }

    private static boolean isSmallChange(ControlState previous, ControlState current, double deadzone) {
        if (Math.abs(current.throttle() - previous.throttle()) > deadzone) {
            return false;
        }
        if (Math.abs(current.yaw() - previous.yaw()) > deadzone) {
            return false;
        }
        if (Math.abs(current.pitch() - previous.pitch()) > deadzone) {
            return false;
        }
        if (Math.abs(current.roll() - previous.roll()) > deadzone) {
            return false;
        }
        return current.flags().equals(previous.flags());
    }

    private static ControlState copy(ControlState state) {
        EnumSet<ControlFlag> flags = state.flags().isEmpty()
                ? EnumSet.noneOf(ControlFlag.class)
                : EnumSet.copyOf(state.flags());
        return new ControlState(state.throttle(), state.yaw(), state.pitch(), state.roll(), flags);
    }

    private record NodeKey(ResourceKey<Level> dimension, BlockPos nodePos) {
        private NodeKey(ResourceKey<Level> dimension, BlockPos nodePos) {
            this.dimension = dimension;
            this.nodePos = nodePos.immutable();
        }
    }

    private record ControllerKey(ResourceKey<Level> dimension, UUID controllerId, BlockPos nodePos) {
        private ControllerKey(ResourceKey<Level> dimension, UUID controllerId, BlockPos nodePos) {
            this.dimension = dimension;
            this.controllerId = controllerId;
            this.nodePos = nodePos.immutable();
        }
    }

    private record ControllerIdentityKey(ResourceKey<Level> dimension, UUID controllerId, BlockPos nodePos) {
        private ControllerIdentityKey(ResourceKey<Level> dimension, UUID controllerId, BlockPos nodePos) {
            this.dimension = dimension;
            this.controllerId = controllerId;
            this.nodePos = nodePos.immutable();
        }
    }

    private record LevelTickKey(ResourceKey<Level> dimension, long tick) {
    }

    private record ClassLevelTickKey(ResourceKey<Level> dimension, long tick, VehicleClass vehicleClass) {
    }

    private record CachedControl(ControlState state, long lastAppliedTick) {
    }

    private record WindowCounter(long windowStartTick, int count) {
    }

    private record IdleState(int idleTicks, long lastPassThroughTick, long lastSeenTick) {
    }
}
