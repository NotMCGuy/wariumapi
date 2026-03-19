package com.wariumapi.aimer;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface AimerService {
    boolean linkNode(Level level, BlockPos launcherPos, BlockPos targetPos);

    boolean clearNode(Level level, BlockPos launcherPos);

    Optional<BlockPos> getLinkedTarget(Level level, BlockPos launcherPos);
}
