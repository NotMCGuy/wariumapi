package com.wariumapi.munitions;

import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Registry and query API for existing Warium missiles and bombs.
 */
public interface MunitionsService {
    void registerWarhead(ResourceLocation id, WarheadProfile profile);

    void registerMunition(ResourceLocation id, MunitionProfile profile);

    void bindMunition(ResourceLocation profileId, EntityType<?> entityType);

    void bindMunition(ResourceLocation profileId, Item item);

    void bindMunitionItemTag(ResourceLocation profileId, TagKey<Item> itemTag);

    Optional<WarheadProfile> getWarhead(ResourceLocation id);

    Optional<MunitionProfile> getMunition(ResourceLocation id);

    Optional<MunitionProfile> getMunition(Entity entity);

    Optional<MunitionProfile> getMunition(ItemStack stack);

    Collection<ResourceLocation> listMunitions();

    Collection<ResourceLocation> listWarheads();
}