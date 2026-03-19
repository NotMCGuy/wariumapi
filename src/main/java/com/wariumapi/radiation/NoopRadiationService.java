package com.wariumapi.radiation;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

final class NoopRadiationService implements IRadiationService {
    static final NoopRadiationService INSTANCE = new NoopRadiationService();

    private NoopRadiationService() {
    }

    @Override
    public RadiationReading sample(Level level, Vec3 pos, net.minecraft.world.entity.Entity entity, RadiationContext context) {
        return new RadiationReading(0.0f);
    }

    @Override
    public void onNuclearDetonation(ServerLevel level, BlockPos pos, float yield, DetonationContext context) {
    }

    @Override
    public void onReactorIncident(ServerLevel level, BlockPos pos, IncidentSeverity severity, IncidentContext context) {
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

