package com.wariumapi.munitions.profile;

import com.wariumapi.munitions.WarheadFlag;
import java.util.EnumSet;
import java.util.Map;

/**
 * Immutable warhead definition mirroring Warium's existing warheads.
 */
public record WarheadProfile(
        double explosiveMass,
        double blastRadiusScale,
        double penetrationFactor,
        EnumSet<WarheadFlag> flags,
        Map<String, Double> extras
) {
    public WarheadProfile {
        if (flags == null) {
            flags = EnumSet.noneOf(WarheadFlag.class);
        }
        if (extras == null) {
            extras = Map.of();
        }
    }
}