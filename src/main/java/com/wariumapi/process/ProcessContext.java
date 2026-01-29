package com.wariumapi.process;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Context for a machine/process event.
 */
public record ProcessContext(
        Level level,
        BlockPos pos,
        ProcessRecipe recipe
) {
}

