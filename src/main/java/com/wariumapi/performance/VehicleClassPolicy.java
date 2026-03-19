package com.wariumapi.performance;

import com.wariumapi.event.VehicleControlInputEvent;
import com.wariumapi.vehicle.ControlFlag;
import com.wariumapi.vehicle.ControlState;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class VehicleClassPolicy {
    private static volatile Set<UUID> capitalControllers = Set.of();
    private static volatile long lastRefreshMillis;

    private VehicleClassPolicy() {
    }

    public static VehicleClass resolve(VehicleControlInputEvent event, ControlState state) {
        UUID controllerId = event.getNode().getControllerId().orElse(null);
        if (controllerId != null && capitalControllers().contains(controllerId)) {
            return VehicleClass.CAPITAL;
        }

        if (state.flags().contains(ControlFlag.LANDING_GEAR)) {
            return VehicleClass.GROUND;
        }

        double pitchAbs = Math.abs(state.pitch());
        double rollAbs = Math.abs(state.roll());
        double yawAbs = Math.abs(state.yaw());
        if (pitchAbs >= PerformanceGuardConfig.AIR_CLASS_MIN_PITCH_ABS.get()
                || rollAbs >= PerformanceGuardConfig.AIR_CLASS_MIN_ROLL_ABS.get()
                || yawAbs >= PerformanceGuardConfig.AIR_CLASS_MIN_YAW_ABS.get()) {
            return VehicleClass.AIR;
        }

        return VehicleClass.GROUND;
    }

    public static int perTickBudget(VehicleClass vehicleClass) {
        return switch (vehicleClass) {
            case GROUND -> PerformanceGuardConfig.CLASS_BUDGET_GROUND_UPDATES_PER_LEVEL_PER_TICK.get();
            case AIR -> PerformanceGuardConfig.CLASS_BUDGET_AIR_UPDATES_PER_LEVEL_PER_TICK.get();
            case CAPITAL -> PerformanceGuardConfig.CLASS_BUDGET_CAPITAL_UPDATES_PER_LEVEL_PER_TICK.get();
        };
    }

    private static Set<UUID> capitalControllers() {
        long now = System.currentTimeMillis();
        if (now - lastRefreshMillis < 1000L) {
            return capitalControllers;
        }

        Set<UUID> parsed = new HashSet<>();
        List<? extends String> values = PerformanceGuardConfig.CAPITAL_CONTROLLER_IDS.get();
        for (String raw : values) {
            if (raw == null || raw.isBlank()) {
                continue;
            }
            try {
                parsed.add(UUID.fromString(raw.trim()));
            } catch (IllegalArgumentException ignored) {
            }
        }

        capitalControllers = Set.copyOf(parsed);
        lastRefreshMillis = now;
        return capitalControllers;
    }
}
