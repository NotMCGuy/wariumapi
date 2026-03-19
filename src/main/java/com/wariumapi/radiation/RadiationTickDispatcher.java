package com.wariumapi.radiation;

import com.wariumapi.WariumApiMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WariumApiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class RadiationTickDispatcher {
    private RadiationTickDispatcher() {
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        MinecraftServer server = event.getServer();
        IRadiationService service = RadiationServices.get();
        if (service.isEnabled()) {
            service.tickServer(server);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.level instanceof ServerLevel)) {
            return;
        }
        IRadiationService service = RadiationServices.get();
        if (service.isEnabled()) {
            service.tickLevel((ServerLevel)event.level);
        }
    }
}

