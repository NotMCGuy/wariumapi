package com.wariumapi.radiation;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class RadiationReading {
    private float intensity;
    private final EnumSet<RadiationType> types;
    private final Map<String, Float> breakdown;

    public RadiationReading(float intensity) {
        this(intensity, EnumSet.noneOf(RadiationType.class), Map.of());
    }

    public RadiationReading(float intensity, EnumSet<RadiationType> types, Map<String, Float> breakdown) {
        this.intensity = intensity;
        this.types = types == null ? EnumSet.noneOf(RadiationType.class) : EnumSet.copyOf(types);
        this.breakdown = new HashMap<>(breakdown == null ? Map.of() : breakdown);
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public EnumSet<RadiationType> getTypes() {
        return EnumSet.copyOf(types);
    }

    public void addType(RadiationType type) {
        if (type != null) {
            this.types.add(type);
        }
    }

    public Map<String, Float> getBreakdown() {
        return Collections.unmodifiableMap(breakdown);
    }

    public void putBreakdown(String key, float value) {
        if (key != null && !key.isEmpty()) {
            breakdown.put(key, value);
        }
    }
}

