package com.wariumapi.ballistics;

import java.util.Collection;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;


public interface BallisticsService {
    void registerProfile(ResourceLocation id, ProjectileProfile profile);

    void bindProfile(ResourceLocation profileId, EntityType<?> entityType);

    void bindProfile(ResourceLocation profileId, Class<? extends Entity> entityClass);

    Optional<ProjectileProfile> getProfile(ResourceLocation id);

    Optional<ProjectileProfile> getProfile(Entity projectileEntity);

    Collection<ResourceLocation> listProfiles();

    default BallisticsFireResult fire(BallisticsFireRequest request) {
        return BallisticsFireResult.failure("UNSUPPORTED");
    }

    default Optional<ResourceLocation> resolveProfileId(
            @Nullable Entity shooter,
            @Nullable ItemStack ammoStack,
            @Nullable ResourceLocation explicitProfileId
    ) {
        return Optional.ofNullable(explicitProfileId);
    }
}

