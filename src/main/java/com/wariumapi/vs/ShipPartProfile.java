package com.wariumapi.vs;

import java.util.Collections;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;


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

