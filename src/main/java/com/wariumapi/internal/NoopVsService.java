package com.wariumapi.internal;

import com.wariumapi.vs.ShipController;
import com.wariumapi.vs.ShipPartProfile;
import com.wariumapi.vs.VsService;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

final class NoopVsService implements VsService {
    @Override
    public void registerShipPart(ResourceLocation id, ShipPartProfile profile) {
    }

    @Override
    public void bindShipPart(ResourceLocation profileId, Item item) {
    }

    @Override
    public void bindShipPart(ResourceLocation profileId, Block block) {
    }

    @Override
    public void bindShipPartItemTag(ResourceLocation profileId, TagKey<Item> itemTag) {
    }

    @Override
    public void bindShipPartBlockTag(ResourceLocation profileId, TagKey<Block> blockTag) {
    }

    @Override
    public Optional<ShipPartProfile> getShipPartProfile(ResourceLocation id) {
        return Optional.empty();
    }

    @Override
    public Optional<ShipPartProfile> getShipPartProfile(ItemStack stack) {
        return Optional.empty();
    }

    @Override
    public Optional<ShipPartProfile> getShipPartProfile(BlockState state) {
        return Optional.empty();
    }

    @Override
    public Optional<ShipController> getShipController(Level level, BlockPos pos) {
        return Optional.empty();
    }
}

