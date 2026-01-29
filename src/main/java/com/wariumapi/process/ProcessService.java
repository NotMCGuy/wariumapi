package com.wariumapi.process;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;

/**
 * Access to Warium machine/process recipes.
 */
public interface ProcessService {
    Collection<ProcessRecipe> getRecipes(ResourceLocation processType);

    Optional<ProcessRecipe> getRecipe(ResourceLocation id);
}

