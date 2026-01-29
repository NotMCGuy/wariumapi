package com.wariumapi.ballistics;

/**
 * Mutable impact result; listeners can adjust values before Warium applies them.
 */
public final class ImpactResult {
    private boolean didPenetrate;
    private double penetrationDepth;
    private double damageMultiplier = 1.0;
    private boolean ricochet;
    private int spallCount;
    private boolean explosionTriggered;

    public boolean didPenetrate() {
        return didPenetrate;
    }

    public void setDidPenetrate(boolean didPenetrate) {
        this.didPenetrate = didPenetrate;
    }

    public double penetrationDepth() {
        return penetrationDepth;
    }

    public void setPenetrationDepth(double penetrationDepth) {
        this.penetrationDepth = penetrationDepth;
    }

    public double damageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public boolean ricochet() {
        return ricochet;
    }

    public void setRicochet(boolean ricochet) {
        this.ricochet = ricochet;
    }

    public int spallCount() {
        return spallCount;
    }

    public void setSpallCount(int spallCount) {
        this.spallCount = spallCount;
    }

    public boolean explosionTriggered() {
        return explosionTriggered;
    }

    public void setExplosionTriggered(boolean explosionTriggered) {
        this.explosionTriggered = explosionTriggered;
    }
}

