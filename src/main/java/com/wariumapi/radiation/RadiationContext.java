package com.wariumapi.radiation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RadiationContext {
    private final Level level;
    private final Vec3 position;
    @Nullable
    private final Entity entity;
    private final long gameTime;
    private final Set<String> flags;

    public RadiationContext(Level level, Vec3 position, @Nullable Entity entity, long gameTime, Set<String> flags) {
        this.level = level;
        this.position = position;
        this.entity = entity;
        this.gameTime = gameTime;
        this.flags = new HashSet<>(flags == null ? Set.of() : flags);
    }

    public static RadiationContext of(Level level, Vec3 position, @Nullable Entity entity) {
        return new RadiationContext(level, position, entity, level.getGameTime(), Set.of());
    }

    public Level getLevel() {
        return level;
    }

    public Vec3 getPosition() {
        return position;
    }

    @Nullable
    public Entity getEntity() {
        return entity;
    }

    public long getGameTime() {
        return gameTime;
    }

    public Set<String> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    public boolean hasFlag(String flag) {
        return flag != null && flags.contains(flag);
    }
}

