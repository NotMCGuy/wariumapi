package com.wariumapi.internal;

import com.wariumapi.armor.ArmorProfile;
import com.wariumapi.armor.ArmorService;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

final class NoopArmorService implements ArmorService {
    @Override
    public void registerProfile(ResourceLocation id, ArmorProfile profile) {
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
    public Optional<ArmorProfile> getProfile(ResourceLocation id) {
        return Optional.empty();
    }

    @Override
    public Optional<ArmorProfile> getProfile(ItemStack stack) {
        return Optional.empty();
    }

    @Override
    public Optional<ArmorProfile> getProfile(BlockState state) {
        return Optional.empty();
    }

    @Override
    public Collection<ResourceLocation> listProfiles() {
        return Collections.emptyList();
    }
}

