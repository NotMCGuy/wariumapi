package com.wariumapi.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public record WrenchContext(
        Level level,
        Player player,
        BlockPos pos,
        BlockState state,
        ItemStack tool,
        Direction face,
        InteractionHand hand,
        boolean isSneaking
) {
}
