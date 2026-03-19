package com.wariumapi.performance;

import net.minecraftforge.common.ForgeConfigSpec;
import java.util.List;

public final class PerformanceGuardConfig {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.DoubleValue LOW_TPS_THRESHOLD;
    public static final ForgeConfigSpec.DoubleValue CRITICAL_TPS_THRESHOLD;
    public static final ForgeConfigSpec.DoubleValue IMPACT_DAMAGE_MULTIPLIER_UNDER_LOAD;
    public static final ForgeConfigSpec.BooleanValue CANCEL_DETONATIONS_UNDER_CRITICAL_LOAD;
        public static final ForgeConfigSpec.DoubleValue DETONATION_CANCEL_CHANCE_DEGRADED;
        public static final ForgeConfigSpec.DoubleValue DETONATION_CANCEL_CHANCE_CRITICAL;
        public static final ForgeConfigSpec.IntValue DETONATION_MAX_EVENTS_PER_LEVEL_PER_TICK;
    public static final ForgeConfigSpec.DoubleValue CONTROL_INPUT_DEADZONE;
    public static final ForgeConfigSpec.IntValue CONTROL_INPUT_MIN_INTERVAL_TICKS;
    public static final ForgeConfigSpec.IntValue CONTROL_CACHE_TTL_TICKS;
        public static final ForgeConfigSpec.BooleanValue CONTROL_BACKPRESSURE_ENABLED;
        public static final ForgeConfigSpec.IntValue CONTROL_BACKPRESSURE_WINDOW_TICKS;
        public static final ForgeConfigSpec.IntValue CONTROL_BACKPRESSURE_MAX_UPDATES_PER_WINDOW;
        public static final ForgeConfigSpec.IntValue CONTROL_MAX_UPDATES_PER_LEVEL_PER_TICK;
        public static final ForgeConfigSpec.BooleanValue VEHICLE_CLASS_BUDGETS_ENABLED;
        public static final ForgeConfigSpec.IntValue CLASS_BUDGET_GROUND_UPDATES_PER_LEVEL_PER_TICK;
        public static final ForgeConfigSpec.IntValue CLASS_BUDGET_AIR_UPDATES_PER_LEVEL_PER_TICK;
        public static final ForgeConfigSpec.IntValue CLASS_BUDGET_CAPITAL_UPDATES_PER_LEVEL_PER_TICK;
        public static final ForgeConfigSpec.DoubleValue AIR_CLASS_MIN_PITCH_ABS;
        public static final ForgeConfigSpec.DoubleValue AIR_CLASS_MIN_ROLL_ABS;
        public static final ForgeConfigSpec.DoubleValue AIR_CLASS_MIN_YAW_ABS;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CAPITAL_CONTROLLER_IDS;
        public static final ForgeConfigSpec.BooleanValue IDLE_SLEEP_ENABLED;
        public static final ForgeConfigSpec.IntValue IDLE_SLEEP_AFTER_TICKS;
        public static final ForgeConfigSpec.IntValue IDLE_SLEEP_PASS_THROUGH_INTERVAL_TICKS;
        public static final ForgeConfigSpec.DoubleValue IDLE_WAKE_THRESHOLD;
        public static final ForgeConfigSpec.BooleanValue DIAGNOSTIC_LOGGING;
        public static final ForgeConfigSpec.IntValue TELEMETRY_LOG_INTERVAL_SECONDS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("performance_guard");
        ENABLED = builder
                .comment("Enable performance guard behavior for load shedding.")
                .define("enabled", false);
        LOW_TPS_THRESHOLD = builder
                .comment("TPS threshold below which the guard enters degraded mode.")
                .defineInRange("lowTpsThreshold", 18.0, 5.0, 20.0);
        CRITICAL_TPS_THRESHOLD = builder
                .comment("TPS threshold below which critical-mode rules are applied.")
                .defineInRange("criticalTpsThreshold", 14.0, 2.0, 20.0);
        IMPACT_DAMAGE_MULTIPLIER_UNDER_LOAD = builder
                .comment("Damage multiplier applied to projectile impacts in degraded mode.")
                .defineInRange("impactDamageMultiplierUnderLoad", 0.75, 0.0, 1.0);
        CANCEL_DETONATIONS_UNDER_CRITICAL_LOAD = builder
                .comment("Cancel munition detonations in critical mode to reduce spikes.")
                .define("cancelDetonationsUnderCriticalLoad", true);
        DETONATION_CANCEL_CHANCE_DEGRADED = builder
                .comment("Chance [0..1] to cancel a munition detonation in degraded mode.")
                .defineInRange("detonationCancelChanceDegraded", 0.0, 0.0, 1.0);
        DETONATION_CANCEL_CHANCE_CRITICAL = builder
                .comment("Chance [0..1] to cancel a munition detonation in critical mode.")
                .defineInRange("detonationCancelChanceCritical", 1.0, 0.0, 1.0);
        DETONATION_MAX_EVENTS_PER_LEVEL_PER_TICK = builder
                .comment("Maximum detonation events allowed per level per tick before extra events are canceled.")
                .defineInRange("detonationMaxEventsPerLevelPerTick", 6, 1, 2000);
        CONTROL_INPUT_DEADZONE = builder
                .comment("Ignore tiny vehicle control changes below this absolute delta.")
                .defineInRange("controlInputDeadzone", 0.02, 0.0, 1.0);
        CONTROL_INPUT_MIN_INTERVAL_TICKS = builder
                .comment("Minimum ticks between vehicle control updates when unchanged.")
                .defineInRange("controlInputMinIntervalTicks", 2, 1, 20);
        CONTROL_CACHE_TTL_TICKS = builder
                .comment("How long to keep node-control cache entries alive.")
                .defineInRange("controlCacheTtlTicks", 1200, 200, 72000);
        CONTROL_BACKPRESSURE_ENABLED = builder
                .comment("Enable per-ship control update backpressure.")
                .define("controlBackpressureEnabled", true);
        CONTROL_BACKPRESSURE_WINDOW_TICKS = builder
                .comment("Backpressure time window in ticks.")
                .defineInRange("controlBackpressureWindowTicks", 20, 5, 200);
        CONTROL_BACKPRESSURE_MAX_UPDATES_PER_WINDOW = builder
                .comment("Max accepted control updates per ship per window in degraded/critical mode.")
                .defineInRange("controlBackpressureMaxUpdatesPerWindow", 8, 1, 200);
        CONTROL_MAX_UPDATES_PER_LEVEL_PER_TICK = builder
                .comment("Maximum accepted control updates per level per tick before extra updates are suppressed.")
                .defineInRange("controlMaxUpdatesPerLevelPerTick", 120, 1, 20000);
        VEHICLE_CLASS_BUDGETS_ENABLED = builder
                .comment("Enable separate control update budgets for ground, air, and capital classes.")
                .define("vehicleClassBudgetsEnabled", true);
        CLASS_BUDGET_GROUND_UPDATES_PER_LEVEL_PER_TICK = builder
                .comment("Per-level per-tick accepted control updates for ground class.")
                .defineInRange("classBudgetGroundUpdatesPerLevelPerTick", 70, 1, 20000);
        CLASS_BUDGET_AIR_UPDATES_PER_LEVEL_PER_TICK = builder
                .comment("Per-level per-tick accepted control updates for air class.")
                .defineInRange("classBudgetAirUpdatesPerLevelPerTick", 40, 1, 20000);
        CLASS_BUDGET_CAPITAL_UPDATES_PER_LEVEL_PER_TICK = builder
                .comment("Per-level per-tick accepted control updates for capital class.")
                .defineInRange("classBudgetCapitalUpdatesPerLevelPerTick", 20, 1, 20000);
        AIR_CLASS_MIN_PITCH_ABS = builder
                .comment("Pitch absolute threshold to classify as air when not grounded.")
                .defineInRange("airClassMinPitchAbs", 0.2, 0.0, 1.0);
        AIR_CLASS_MIN_ROLL_ABS = builder
                .comment("Roll absolute threshold to classify as air when not grounded.")
                .defineInRange("airClassMinRollAbs", 0.2, 0.0, 1.0);
        AIR_CLASS_MIN_YAW_ABS = builder
                .comment("Yaw absolute threshold to classify as air when not grounded.")
                .defineInRange("airClassMinYawAbs", 0.3, 0.0, 1.0);
        CAPITAL_CONTROLLER_IDS = builder
                .comment("Controller UUIDs treated as capital class for stricter budgeting.")
                .defineListAllowEmpty(List.of("capitalControllerIds"), List.of(), entry -> entry instanceof String);
        IDLE_SLEEP_ENABLED = builder
                .comment("Enable idle sleep suppression for parked vehicles.")
                .define("idleSleepEnabled", true);
        IDLE_SLEEP_AFTER_TICKS = builder
                .comment("Ticks of near-zero input before idle sleep activates.")
                .defineInRange("idleSleepAfterTicks", 80, 5, 72000);
        IDLE_SLEEP_PASS_THROUGH_INTERVAL_TICKS = builder
                .comment("Allow one control update through while idle every N ticks.")
                .defineInRange("idleSleepPassThroughIntervalTicks", 20, 1, 72000);
        IDLE_WAKE_THRESHOLD = builder
                .comment("Absolute input delta required to wake from idle sleep.")
                .defineInRange("idleWakeThreshold", 0.08, 0.0, 1.0);
        DIAGNOSTIC_LOGGING = builder
                .comment("Enable periodic diagnostic logging for guard transitions and counters.")
                .define("diagnosticLogging", true);
        TELEMETRY_LOG_INTERVAL_SECONDS = builder
                .comment("How often telemetry counters are written to logs.")
                .defineInRange("telemetryLogIntervalSeconds", 30, 5, 600);
        builder.pop();

        SPEC = builder.build();
    }

    private PerformanceGuardConfig() {
    }
}
