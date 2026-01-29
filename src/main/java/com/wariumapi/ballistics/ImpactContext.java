package com.wariumapi.ballistics;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**
 * Context for a projectile impact.
 *
 * <p>Some fields may be null depending on the hit type.</p>
 */
public record ImpactContext(
        Level level,
        Entity shooter,
        Entity target,
        Vec3 hitPos,
        Vec3 hitNormal,
        BlockState blockState,
        Entity projectile,
        ProjectileProfile projectileProfile
) {
}

