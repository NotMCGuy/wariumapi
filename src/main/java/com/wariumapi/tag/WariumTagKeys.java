package com.wariumapi.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.Registries;

/**
 * Warium API tag keys.
 */
public final class WariumTagKeys {
    public static final class Items {
        public static final TagKey<Item> ARMOR_MATERIALS = tag("armor_materials");
        public static final TagKey<Item> SHIP_PARTS = tag("ship_parts");

        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registries.ITEM, new ResourceLocation("warium", path));
        }

        private Items() {
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> STRUCTURAL_BLOCKS = tag("structural_blocks");
        public static final TagKey<Block> SHIP_PARTS = tag("ship_parts");

        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation("warium", path));
        }

        private Blocks() {
        }
    }

    private WariumTagKeys() {
    }
}

