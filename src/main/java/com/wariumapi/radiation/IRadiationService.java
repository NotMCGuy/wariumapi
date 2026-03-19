package com.wariumapi.radiation;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface IRadiationService {
    RadiationReading sample(Level level, Vec3 pos, @Nullable Entity entity, RadiationContext context);

    default void tickServer(MinecraftServer server) {
    }

    default void tickLevel(ServerLevel level) {
    }

    void onNuclearDetonation(ServerLevel level, BlockPos pos, float yield, DetonationContext context);

    void onReactorIncident(ServerLevel level, BlockPos pos, IncidentSeverity severity, IncidentContext context);

    boolean isEnabled();
}

