package com.wariumapi.process;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;


public interface ProcessService {
    Collection<ProcessRecipe> getRecipes(ResourceLocation processType);

    Optional<ProcessRecipe> getRecipe(ResourceLocation id);
}

