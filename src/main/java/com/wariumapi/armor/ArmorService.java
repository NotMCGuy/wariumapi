package com.wariumapi.armor;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Registry and query API for armor profiles.
 */
public interface ArmorService {
    void registerProfile(ResourceLocation id, ArmorProfile profile);

    void bindProfile(ResourceLocation profileId, Item item);

    void bindProfile(ResourceLocation profileId, Block block);

    void bindProfileItemTag(ResourceLocation profileId, TagKey<Item> itemTag);

    void bindProfileBlockTag(ResourceLocation profileId, TagKey<Block> blockTag);

    Optional<ArmorProfile> getProfile(ResourceLocation id);

    Optional<ArmorProfile> getProfile(ItemStack stack);

    Optional<ArmorProfile> getProfile(BlockState state);

    Collection<ResourceLocation> listProfiles();
}

