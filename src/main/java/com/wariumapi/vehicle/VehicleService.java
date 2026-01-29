package com.wariumapi.vehicle;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * API for interacting with existing WariumVS vehicle control nodes.
 */
public interface VehicleService {
    Optional<VehicleControlNode> getNodeAt(Level level, BlockPos pos);

    Optional<VehicleController> getVehicleController(Level level, BlockPos pos);

    boolean isVehicleControlNode(BlockState state);
}