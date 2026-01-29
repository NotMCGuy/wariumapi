package com.wariumapi.vs;

import java.util.Collections;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;

/**
 * Immutable ship part profile for WariumVS integration.
 */
public record ShipPartProfile(
        double mass,
        double buoyancy,
        double drag,
        double lift,
        Map<ResourceLocation, Double> extras
) {
    public ShipPartProfile {
        if (extras == null) {
            extras = Collections.emptyMap();
        }
    }
}

