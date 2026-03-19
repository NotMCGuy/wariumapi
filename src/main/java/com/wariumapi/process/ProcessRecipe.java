package com.wariumapi.process;

import net.minecraft.resources.ResourceLocation;


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

