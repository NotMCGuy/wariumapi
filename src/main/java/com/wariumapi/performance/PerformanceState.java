package com.wariumapi.performance;

public final class PerformanceState {
    public enum GuardMode {
        DISABLED,
        NORMAL,
        DEGRADED,
        CRITICAL
    }

    private static volatile double currentTps = 20.0;

    private PerformanceState() {
    }

    public static void setCurrentTps(double tps) {
        currentTps = Math.max(0.0, Math.min(20.0, tps));
    }

    public static double getCurrentTps() {
        return currentTps;
    }

    public static boolean isEnabled() {
        return PerformanceGuardConfig.ENABLED.get();
    }

    public static boolean isDegraded() {
        return isEnabled() && currentTps < PerformanceGuardConfig.LOW_TPS_THRESHOLD.get();
    }

    public static boolean isCritical() {
        return isEnabled() && currentTps < PerformanceGuardConfig.CRITICAL_TPS_THRESHOLD.get();
    }

    public static GuardMode getMode() {
        if (!isEnabled()) {
            return GuardMode.DISABLED;
        }
        if (isCritical()) {
            return GuardMode.CRITICAL;
        }
        if (isDegraded()) {
            return GuardMode.DEGRADED;
        }
        return GuardMode.NORMAL;
    }
}
