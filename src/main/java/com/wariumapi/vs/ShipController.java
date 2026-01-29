package com.wariumapi.vs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

/**
 * Minimal ship control surface for applying forces/rotations.
 */
public interface ShipController {
    void addForce(BlockPos pos, Vec3 direction, double throttle, ForceMode mode, ForceDirectionMode directionMode);

    void addRotation(Vec3 rotation, ForceMode mode);

    void setNumber(String key, Number value);

    void setBoolean(String key, boolean value);

    void setString(String key, String value);
}

