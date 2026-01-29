package com.wariumapi.event;

import com.wariumapi.vehicle.VehicleControlNode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired when a vehicle control node is linked/activated.
 */
public class VehicleControlNodeActivatedEvent extends Event {
    private final Level level;
    private final BlockPos nodePos;
    private final VehicleControlNode node;
    private final Player player;

    public VehicleControlNodeActivatedEvent(Level level, BlockPos nodePos, VehicleControlNode node, Player player) {
        this.level = level;
        this.nodePos = nodePos;
        this.node = node;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }
}