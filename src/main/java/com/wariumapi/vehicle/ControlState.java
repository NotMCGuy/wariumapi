package com.wariumapi.vehicle;

import java.util.EnumSet;

/**
 * Immutable snapshot of current vehicle control inputs.
 */
public record ControlState(
        float throttle,
        float yaw,
        float pitch,
        float roll,
        EnumSet<ControlFlag> flags
) {
    public ControlState {
        if (flags == null) {
            flags = EnumSet.noneOf(ControlFlag.class);
        }
    }
}