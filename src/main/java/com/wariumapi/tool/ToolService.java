package com.wariumapi.tool;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

/**
 * Service for registering and querying wrench-like tools.
 */
public interface ToolService {
    boolean isWrench(ItemStack stack);

    void registerWrench(ItemLike item);

    void registerWrenchTag(TagKey<Item> tag);
}
