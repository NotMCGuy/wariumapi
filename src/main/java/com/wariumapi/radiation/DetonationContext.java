package com.wariumapi.radiation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;

public class DetonationContext {
    private final String sourceModId;
    @Nullable
    private final Entity sourceEntity;
    private final Set<String> flags;

    public DetonationContext(String sourceModId, @Nullable Entity sourceEntity, Set<String> flags) {
        this.sourceModId = sourceModId == null ? "unknown" : sourceModId;
        this.sourceEntity = sourceEntity;
        this.flags = new HashSet<>(flags == null ? Set.of() : flags);
    }

    public static DetonationContext of(String sourceModId, @Nullable Entity sourceEntity) {
        return new DetonationContext(sourceModId, sourceEntity, Set.of());
    }

    public String getSourceModId() {
        return sourceModId;
    }

    @Nullable
    public Entity getSourceEntity() {
        return sourceEntity;
    }

    public Set<String> getFlags() {
        return Collections.unmodifiableSet(flags);
    }

    public boolean hasFlag(String flag) {
        return flag != null && flags.contains(flag);
    }
}

