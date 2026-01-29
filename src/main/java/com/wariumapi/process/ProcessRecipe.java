package com.wariumapi.process;

import net.minecraft.resources.ResourceLocation;

/**
 * Immutable process recipe definition.
 */
public record ProcessRecipe(
        ResourceLocation id,
        ResourceLocation processType,
        ResourceLocation inputId,
        boolean inputIsTag,
        ResourceLocation processItem,
        ResourceLocation resultItem,
        int runs
) {
}

