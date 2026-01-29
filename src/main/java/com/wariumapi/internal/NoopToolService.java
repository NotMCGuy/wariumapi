package com.wariumapi.internal;

import com.wariumapi.tool.ToolService;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

final class NoopToolService implements ToolService {
    @Override
    public boolean isWrench(ItemStack stack) {
        return false;
    }

    @Override
    public void registerWrench(ItemLike item) {
    }

    @Override
    public void registerWrenchTag(TagKey<Item> tag) {
    }
}
