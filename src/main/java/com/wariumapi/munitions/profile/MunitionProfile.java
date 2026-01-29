package com.wariumapi.munitions.profile;

import com.wariumapi.munitions.MunitionFlag;
import com.wariumapi.munitions.MunitionKind;
import java.util.EnumSet;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;

/**
 * Immutable munition definition mirroring Warium's existing missiles and bombs.
 */
public record MunitionProfile(
        MunitionKind kind,
        double mass,
        double drag,
        ResourceLocation warheadId,
        EnumSet<MunitionFlag> flags,
        Map<String, Double> extras
) {
    public MunitionProfile {
        if (flags == null) {
            flags = EnumSet.noneOf(MunitionFlag.class);
        }
        if (extras == null) {
            extras = Map.of();
        }
    }
}