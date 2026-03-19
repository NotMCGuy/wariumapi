package com.wariumapi.internal;

import com.wariumapi.effects.ExplosionEffectKind;
import com.wariumapi.effects.ExplosionEffectsService;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

final class NoopExplosionEffectsService implements ExplosionEffectsService {
    @Override
    public void playExplosionFx(Level level, double x, double y, double z, ExplosionEffectKind kind) {
    }

    @Override
    public ResourceLocation soundKey(ExplosionEffectKind kind) {
        return null;
    }

    @Override
    public ResourceLocation particleKey(ExplosionEffectKind kind) {
        return null;
    }
}
