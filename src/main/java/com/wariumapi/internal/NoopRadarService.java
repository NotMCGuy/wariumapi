package com.wariumapi.internal;

import com.wariumapi.radar.RadarProfile;
import com.wariumapi.radar.RadarService;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

final class NoopRadarService implements RadarService {
    @Override
    public void registerProfile(ResourceLocation id, RadarProfile profile) {
    }

    @Override
    public void bindProfile(ResourceLocation profileId, Item item) {
    }

    @Override
    public void bindProfile(ResourceLocation profileId, Block block) {
    }

    @Override
    public void bindProfileItemTag(ResourceLocation profileId, TagKey<Item> itemTag) {
    }

    @Override
    public void bindProfileBlockTag(ResourceLocation profileId, TagKey<Block> blockTag) {
    }

    @Override
    public Optional<RadarProfile> getProfile(ResourceLocation id) {
        return Optional.empty();
    }

    @Override
    public Optional<RadarProfile> getProfile(ItemStack stack) {
        return Optional.empty();
    }

    @Override
    public Optional<RadarProfile> getProfile(BlockState state) {
        return Optional.empty();
    }

    @Override
    public boolean isRadar(BlockState state) {
        return false;
    }

    @Override
    public boolean isLargeRadar(BlockState state) {
        return false;
    }
}
