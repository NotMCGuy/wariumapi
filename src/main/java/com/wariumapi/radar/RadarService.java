package com.wariumapi.radar;

import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface RadarService {
    void registerProfile(ResourceLocation id, RadarProfile profile);

    void bindProfile(ResourceLocation profileId, Item item);

    void bindProfile(ResourceLocation profileId, Block block);

    void bindProfileItemTag(ResourceLocation profileId, TagKey<Item> itemTag);

    void bindProfileBlockTag(ResourceLocation profileId, TagKey<Block> blockTag);

    Optional<RadarProfile> getProfile(ResourceLocation id);

    Optional<RadarProfile> getProfile(ItemStack stack);

    Optional<RadarProfile> getProfile(BlockState state);

    boolean isRadar(BlockState state);

    boolean isLargeRadar(BlockState state);
}
