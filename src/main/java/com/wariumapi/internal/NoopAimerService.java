package com.wariumapi.internal;

import com.wariumapi.aimer.AimerService;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

final class NoopAimerService implements AimerService {
    @Override
    public boolean linkNode(Level level, BlockPos launcherPos, BlockPos targetPos) {
        return false;
    }

    @Override
    public boolean clearNode(Level level, BlockPos launcherPos) {
        return false;
    }

    @Override
    public Optional<BlockPos> getLinkedTarget(Level level, BlockPos launcherPos) {
        return Optional.empty();
    }
}
