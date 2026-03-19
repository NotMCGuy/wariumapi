package com.wariumapi.ballistics;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record BallisticsFireRequest(
        Level level,
        Vec3 spawnPos,
        Vec3 initialVelocity,
        @Nullable Entity shooter,
        @Nullable ItemStack ammoStack,
        @Nullable ResourceLocation profileId,
        double massOverride,
        double velocityScale,
        @Nullable EnumSet<ProjectileFlag> flagsOverride
) {
    public BallisticsFireRequest {
        if (velocityScale == 0.0) {
            velocityScale = 1.0;
        }
        if (flagsOverride != null) {
            flagsOverride = EnumSet.copyOf(flagsOverride);
        }
        if (ammoStack != null) {
            ammoStack = ammoStack.copy();
        }
    }

    public static BallisticsFireRequest of(Level level, Vec3 spawnPos, Vec3 initialVelocity) {
        return new BallisticsFireRequest(level, spawnPos, initialVelocity, null, null, null, 0.0, 1.0, null);
    }
}
