package com.wariumapi.performance;

import java.util.concurrent.atomic.LongAdder;

public final class PerformanceGuardTelemetry {
    private static final LongAdder impactEventsScaled = new LongAdder();
    private static final LongAdder detonationsCanceled = new LongAdder();
    private static final LongAdder detonationsCanceledBudget = new LongAdder();
    private static final LongAdder vehicleInputsSuppressed = new LongAdder();
    private static final LongAdder vehicleInputsSuppressedIdleSleep = new LongAdder();
    private static final LongAdder vehicleInputsDroppedBackpressure = new LongAdder();
    private static final LongAdder vehicleInputsDroppedBudget = new LongAdder();
    private static final LongAdder vehicleInputsDroppedClassBudget = new LongAdder();
    private static final LongAdder cacheEntriesEvicted = new LongAdder();

    private PerformanceGuardTelemetry() {
    }

    public static void recordImpactScaled() {
        impactEventsScaled.increment();
    }

    public static void recordDetonationCanceled() {
        detonationsCanceled.increment();
    }

    public static void recordDetonationCanceledBudget() {
        detonationsCanceledBudget.increment();
    }

    public static void recordVehicleInputSuppressed() {
        vehicleInputsSuppressed.increment();
    }

    public static void recordVehicleInputSuppressedIdleSleep() {
        vehicleInputsSuppressedIdleSleep.increment();
    }

    public static void recordVehicleInputDroppedBackpressure() {
        vehicleInputsDroppedBackpressure.increment();
    }

    public static void recordVehicleInputDroppedBudget() {
        vehicleInputsDroppedBudget.increment();
    }

    public static void recordVehicleInputDroppedClassBudget() {
        vehicleInputsDroppedClassBudget.increment();
    }

    public static void recordCacheEntryEvicted() {
        cacheEntriesEvicted.increment();
    }

    public static Snapshot snapshotAndReset() {
        return new Snapshot(
                impactEventsScaled.sumThenReset(),
                detonationsCanceled.sumThenReset(),
                detonationsCanceledBudget.sumThenReset(),
                vehicleInputsSuppressed.sumThenReset(),
                vehicleInputsSuppressedIdleSleep.sumThenReset(),
                vehicleInputsDroppedBackpressure.sumThenReset(),
                vehicleInputsDroppedBudget.sumThenReset(),
                vehicleInputsDroppedClassBudget.sumThenReset(),
                cacheEntriesEvicted.sumThenReset()
        );
    }

    public record Snapshot(
            long impactEventsScaled,
            long detonationsCanceled,
            long detonationsCanceledBudget,
            long vehicleInputsSuppressed,
            long vehicleInputsSuppressedIdleSleep,
            long vehicleInputsDroppedBackpressure,
            long vehicleInputsDroppedBudget,
            long vehicleInputsDroppedClassBudget,
            long cacheEntriesEvicted
    ) {
        public boolean isEmpty() {
            return impactEventsScaled == 0L
                    && detonationsCanceled == 0L
                    && detonationsCanceledBudget == 0L
                    && vehicleInputsSuppressed == 0L
                    && vehicleInputsSuppressedIdleSleep == 0L
                    && vehicleInputsDroppedBackpressure == 0L
                    && vehicleInputsDroppedBudget == 0L
                    && vehicleInputsDroppedClassBudget == 0L
                    && cacheEntriesEvicted == 0L;
        }
    }
}
