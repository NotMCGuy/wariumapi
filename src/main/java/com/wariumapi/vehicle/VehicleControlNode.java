package com.wariumapi.vehicle;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Read-only view of an existing vehicle control node block entity.
 */
public interface VehicleControlNode {
    Level getLevel();

    BlockPos getNodePos();

    /**
     * @return optional controller UUID if the node has one stored
     */
    Optional<UUID> getControllerId();

    /**
     * @return true if the node is currently receiving control input
     */
    boolean isActive();

    Optional<VehicleController> getVehicle();
}