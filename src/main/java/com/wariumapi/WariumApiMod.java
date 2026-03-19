package com.wariumapi;

import com.wariumapi.event.RadiationServiceRegisterEvent;
import com.wariumapi.performance.PerformanceGuardConfig;
import com.wariumapi.radiation.RadiationApiConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WariumApiMod.MOD_ID)
public class WariumApiMod {
    public static final String MOD_ID = "wariumapi";

    public WariumApiMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RadiationApiConfig.SPEC, "wariumapi-radiation.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PerformanceGuardConfig.SPEC, "wariumapi-performance.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> MinecraftForge.EVENT_BUS.post(new RadiationServiceRegisterEvent()));
    }
}
