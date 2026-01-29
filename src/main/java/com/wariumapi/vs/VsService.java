package com.wariumapi.vs;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * API for WariumVS ship integration. Implementation is present only when WariumVS is installed.
 */
public interface VsService {
    void registerShipPart(ResourceLocation id, ShipPartProfile profile);

    void bindShipPart(ResourceLocation profileId, Item item);

    void bindShipPart(ResourceLocation profileId, Block block);

    void bindShipPartItemTag(ResourceLocation profileId, TagKey<Item> itemTag);

    void bindShipPartBlockTag(ResourceLocation profileId, TagKey<Block> blockTag);

    Optional<ShipPartProfile> getShipPartProfile(ResourceLocation id);

    Optional<ShipPartProfile> getShipPartProfile(ItemStack stack);

    Optional<ShipPartProfile> getShipPartProfile(BlockState state);

    Optional<ShipController> getShipController(Level level, BlockPos pos);
}

