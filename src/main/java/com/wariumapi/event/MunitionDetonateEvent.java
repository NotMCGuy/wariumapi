package com.wariumapi.event;

import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired when a munition detonates. Cancel to suppress the default detonation.
 */
@Cancelable
public class MunitionDetonateEvent extends Event {
    private final Level level;
    private final double x;
    private final double y;
    private final double z;
    private final Entity entity;
    private final MunitionProfile munitionProfile;
    private final WarheadProfile warheadProfile;

    public MunitionDetonateEvent(Level level, double x, double y, double z, Entity entity,
            MunitionProfile munitionProfile, WarheadProfile warheadProfile) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.z = z;
        this.entity = entity;
        this.munitionProfile = munitionProfile;
        this.warheadProfile = warheadProfile;
    }

    public Level getLevel() {
        return level;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Entity getEntity() {
        return entity;
    }

    public MunitionProfile getMunitionProfile() {
        return munitionProfile;
    }

    public WarheadProfile getWarheadProfile() {
        return warheadProfile;
    }
}