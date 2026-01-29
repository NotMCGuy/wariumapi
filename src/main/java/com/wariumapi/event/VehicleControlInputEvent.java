package com.wariumapi.event;

import com.wariumapi.vehicle.ControlState;
import com.wariumapi.vehicle.VehicleControlNode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired after WariumVS applies control input to a vehicle control node.
 */
public class VehicleControlInputEvent extends Event {
    private final Level level;
    private final BlockPos nodePos;
    private final VehicleControlNode node;
    private final ControlState state;

    public VehicleControlInputEvent(Level level, BlockPos nodePos, VehicleControlNode node, ControlState state) {
        this.level = level;
        this.nodePos = nodePos;
        this.node = node;
        this.state = state;
    }

    public Level getLevel() {
        return level;
    }

    public BlockPos getNodePos() {
        return nodePos;
    }

    public VehicleControlNode getNode() {
        return node;
    }

    public ControlState getState() {
        return state;
    }
}