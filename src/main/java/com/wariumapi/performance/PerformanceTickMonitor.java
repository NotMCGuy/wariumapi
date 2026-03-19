package com.wariumapi.performance;

import com.mojang.logging.LogUtils;
import com.wariumapi.WariumApiMod;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = WariumApiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PerformanceTickMonitor {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static int ticksSinceSample;
    private static long secondsSinceTelemetryLog;
    private static PerformanceState.GuardMode lastMode = PerformanceState.GuardMode.DISABLED;

    private PerformanceTickMonitor() {
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !PerformanceState.isEnabled()) {
            return;
        }

        ticksSinceSample++;
        if (ticksSinceSample < 20) {
            return;
        }
        ticksSinceSample = 0;

        MinecraftServer server = event.getServer();
        double mspt = server.getAverageTickTime();
        if (mspt <= 0.0) {
            PerformanceState.setCurrentTps(20.0);
        } else {
            PerformanceState.setCurrentTps(1000.0 / mspt);
        }

        maybeLogTransition(mspt);
        maybeLogTelemetry();
    }

    private static void maybeLogTransition(double mspt) {
        PerformanceState.GuardMode mode = PerformanceState.getMode();
        if (mode == lastMode) {
            return;
        }

        if (PerformanceGuardConfig.DIAGNOSTIC_LOGGING.get()) {
            LOGGER.info("[wariumapi/perf] Guard mode transition: {} -> {} (tps={}, mspt={})",
                    lastMode,
                    mode,
                    String.format("%.2f", PerformanceState.getCurrentTps()),
                    String.format("%.2f", mspt));
        }
        lastMode = mode;
    }

    private static void maybeLogTelemetry() {
        secondsSinceTelemetryLog++;
        int interval = PerformanceGuardConfig.TELEMETRY_LOG_INTERVAL_SECONDS.get();
        if (secondsSinceTelemetryLog < interval) {
            return;
        }
        secondsSinceTelemetryLog = 0;

        PerformanceGuardTelemetry.Snapshot snapshot = PerformanceGuardTelemetry.snapshotAndReset();
        if (!PerformanceGuardConfig.DIAGNOSTIC_LOGGING.get() || snapshot.isEmpty()) {
            return;
        }

        LOGGER.info("[wariumapi/perf] Telemetry mode={} tps={} counters: impacts_scaled={}, detonations_canceled={}, detonations_canceled_budget={}, vehicle_inputs_suppressed={}, vehicle_inputs_suppressed_idle_sleep={}, vehicle_inputs_dropped_backpressure={}, vehicle_inputs_dropped_budget={}, vehicle_inputs_dropped_class_budget={}, cache_evicted={}",
                PerformanceState.getMode(),
                String.format("%.2f", PerformanceState.getCurrentTps()),
                snapshot.impactEventsScaled(),
                snapshot.detonationsCanceled(),
            snapshot.detonationsCanceledBudget(),
                snapshot.vehicleInputsSuppressed(),
            snapshot.vehicleInputsSuppressedIdleSleep(),
            snapshot.vehicleInputsDroppedBackpressure(),
            snapshot.vehicleInputsDroppedBudget(),
            snapshot.vehicleInputsDroppedClassBudget(),
                snapshot.cacheEntriesEvicted());
    }
}
