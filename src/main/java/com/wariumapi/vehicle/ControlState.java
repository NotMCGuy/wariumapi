package com.wariumapi.vehicle;

import java.util.EnumSet;


public record ControlState(
        float throttle,
        float yaw,
        float pitch,
        float roll,
        int flapComparator,
        EnumSet<ControlFlag> flags
) {
    public static final int MIN_FLAP_COMPARATOR = 0;
    public static final int MAX_FLAP_COMPARATOR = 15;

    public ControlState {
        flapComparator = clampFlapComparator(flapComparator);
        if (flags == null) {
            flags = EnumSet.noneOf(ControlFlag.class);
        }
    }

    public ControlState(float throttle, float yaw, float pitch, float roll, EnumSet<ControlFlag> flags) {
        this(throttle, yaw, pitch, roll, MIN_FLAP_COMPARATOR, flags);
    }

    public boolean hasFlapInput() {
        return flapComparator > MIN_FLAP_COMPARATOR;
    }

    private static int clampFlapComparator(int value) {
        if (value < MIN_FLAP_COMPARATOR) {
            return MIN_FLAP_COMPARATOR;
        }
        if (value > MAX_FLAP_COMPARATOR) {
            return MAX_FLAP_COMPARATOR;
        }
        return value;
    }
}
