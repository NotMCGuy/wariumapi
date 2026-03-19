package com.wariumapi.ballistics;

import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public record BallisticsFireResult(
        boolean fired,
        @Nullable Entity projectile,
        @Nullable ResourceLocation resolvedProfileId,
        @Nullable String reason
) {
    public static BallisticsFireResult success(Entity projectile, ResourceLocation resolvedProfileId) {
        return new BallisticsFireResult(true, projectile, resolvedProfileId, null);
    }

    public static BallisticsFireResult failure(String reason) {
        return new BallisticsFireResult(false, null, null, reason);
    }

    public static BallisticsFireResult failure(String reason, @Nullable ResourceLocation resolvedProfileId) {
        return new BallisticsFireResult(false, null, resolvedProfileId, reason);
    }
}
