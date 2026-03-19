package com.wariumapi.radar;

import java.util.Map;

public record RadarProfile(
        RadarKind kind,
        double rangeMeters,
        double refreshSeconds,
        double horizontalFovDegrees,
        double verticalFovDegrees,
        Map<String, Double> extras
) {
    public RadarProfile {
        if (kind == null) {
            kind = RadarKind.SMALL;
        }
        if (extras == null) {
            extras = Map.of();
        }
    }
}
