package com.wariumapi.internal;

import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

final class NoopMunitionsService implements MunitionsService {
    @Override
    public void registerWarhead(ResourceLocation id, WarheadProfile profile) {
    }

    @Override
    public void registerMunition(ResourceLocation id, MunitionProfile profile) {
    }

    @Override
    public void bindMunition(ResourceLocation profileId, EntityType<?> entityType) {
    }

    @Override
    public void bindMunition(ResourceLocation profileId, Item item) {
    }

    @Override
    public void bindMunitionItemTag(ResourceLocation profileId, TagKey<Item> itemTag) {
    }

    @Override
    public Optional<WarheadProfile> getWarhead(ResourceLocation id) {
        return Optional.empty();
    }

    @Override
    public Optional<MunitionProfile> getMunition(ResourceLocation id) {
        return Optional.empty();
    }

    @Override
    public Optional<MunitionProfile> getMunition(Entity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<MunitionProfile> getMunition(ItemStack stack) {
        return Optional.empty();
    }

    @Override
    public Collection<ResourceLocation> listMunitions() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ResourceLocation> listWarheads() {
        return Collections.emptyList();
    }
}