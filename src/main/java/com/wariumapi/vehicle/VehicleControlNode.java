package com.wariumapi.vehicle;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;


public interface VehicleControlNode {
    Level getLevel();

    BlockPos getNodePos();

    
    Optional<UUID> getControllerId();

    boolean isActive();

    default int getComparatorValue() {
        return isActive() ? 15 : 0;
    }

    Optional<VehicleController> getVehicle();
}
