package com.wariumapi.ballistics;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

/**
 * Registry and query API for projectile profiles.
 */
public interface BallisticsService {
    void registerProfile(ResourceLocation id, ProjectileProfile profile);

    void bindProfile(ResourceLocation profileId, EntityType<?> entityType);

    void bindProfile(ResourceLocation profileId, Class<? extends Entity> entityClass);

    Optional<ProjectileProfile> getProfile(ResourceLocation id);

    Optional<ProjectileProfile> getProfile(Entity projectileEntity);

    Collection<ResourceLocation> listProfiles();
}

