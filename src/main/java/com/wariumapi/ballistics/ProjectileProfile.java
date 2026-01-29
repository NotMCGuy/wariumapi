package com.wariumapi.ballistics;

import java.util.Collections;
import java.util.EnumSet;

/**
 * Immutable projectile definition.
 */
public record ProjectileProfile(
        double mass,
        double calibre,
        double velocity,
        EnumSet<ProjectileFlag> flags,
        double explosiveMass,
        FuseType fuseType,
        double penetrationFactor,
        double damageScale,
        double spallFactor
) {
    public ProjectileProfile {
        if (flags == null) {
            flags = EnumSet.noneOf(ProjectileFlag.class);
        }
        if (fuseType == null) {
            fuseType = FuseType.NONE;
        }
    }
}

