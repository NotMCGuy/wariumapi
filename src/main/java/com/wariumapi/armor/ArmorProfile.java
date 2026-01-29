package com.wariumapi.armor;

import java.util.Collections;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;

/**
 * Immutable armor material/profile definition used by Warium armor resolution.
 */
public record ArmorProfile(
        double thickness,
        double hardness,
        double toughness,
        double spallFactor,
        double ricochetChance,
        double blastReduction,
        Map<ResourceLocation, Double> extras
) {
    public ArmorProfile {
        if (extras == null) {
            extras = Collections.emptyMap();
        }
    }
}

