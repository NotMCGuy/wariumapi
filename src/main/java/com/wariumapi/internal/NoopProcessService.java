package com.wariumapi.internal;

import com.wariumapi.process.ProcessRecipe;
import com.wariumapi.process.ProcessService;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;

final class NoopProcessService implements ProcessService {
    @Override
    public Collection<ProcessRecipe> getRecipes(ResourceLocation processType) {
        return Collections.emptyList();
    }

    @Override
    public Optional<ProcessRecipe> getRecipe(ResourceLocation id) {
        return Optional.empty();
    }
}

