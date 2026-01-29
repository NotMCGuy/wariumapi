package com.wariumapi.event;

import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired when a munition entity is launched/spawned.
 */
public class MunitionLaunchedEvent extends Event {
    private final Level level;
    private final Entity entity;
    private final MunitionProfile munitionProfile;
    private final WarheadProfile warheadProfile;

    public MunitionLaunchedEvent(Level level, Entity entity, MunitionProfile munitionProfile, WarheadProfile warheadProfile) {
        this.level = level;
        this.entity = entity;
        this.munitionProfile = munitionProfile;
        this.warheadProfile = warheadProfile;
    }

    public Level getLevel() {
        return level;
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