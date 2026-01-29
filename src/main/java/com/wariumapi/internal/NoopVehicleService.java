package com.wariumapi.internal;

import com.wariumapi.vehicle.VehicleControlNode;
import com.wariumapi.vehicle.VehicleController;
import com.wariumapi.vehicle.VehicleService;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

final class NoopVehicleService implements VehicleService {
    @Override
    public Optional<VehicleControlNode> getNodeAt(Level level, BlockPos pos) {
        return Optional.empty();
    }

    @Override
    public Optional<VehicleController> getVehicleController(Level level, BlockPos pos) {
        return Optional.empty();
    }

    @Override
    public boolean isVehicleControlNode(BlockState state) {
        return false;
    }
}