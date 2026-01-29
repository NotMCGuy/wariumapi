package com.wariumapi.internal;

import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.ballistics.ProjectileProfile;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

final class NoopBallisticsService implements BallisticsService {
    @Override
    public void registerProfile(ResourceLocation id, ProjectileProfile profile) {
    }

    @Override
    public void bindProfile(ResourceLocation profileId, EntityType<?> entityType) {
    }

    @Override
    public void bindProfile(ResourceLocation profileId, Class<? extends Entity> entityClass) {
    }

    @Override
    public Optional<ProjectileProfile> getProfile(ResourceLocation id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProjectileProfile> getProfile(Entity projectileEntity) {
        return Optional.empty();
    }

    @Override
    public Collection<ResourceLocation> listProfiles() {
        return Collections.emptyList();
    }
}

