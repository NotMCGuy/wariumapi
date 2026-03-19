package com.wariumapi.effects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public interface ExplosionEffectsService {
    void playExplosionFx(Level level, double x, double y, double z, ExplosionEffectKind kind);

    ResourceLocation soundKey(ExplosionEffectKind kind);

    ResourceLocation particleKey(ExplosionEffectKind kind);
}
