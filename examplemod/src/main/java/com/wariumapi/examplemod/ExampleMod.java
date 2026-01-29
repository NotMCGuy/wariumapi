package com.wariumapi.examplemod;

import com.wariumapi.WariumApi;
import com.wariumapi.armor.ArmorProfile;
import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.FuseType;
import com.wariumapi.ballistics.ProjectileFlag;
import com.wariumapi.ballistics.ProjectileProfile;
import com.wariumapi.event.WariumApiReadyEvent;
import com.wariumapi.munitions.MunitionFlag;
import com.wariumapi.munitions.MunitionKind;
import com.wariumapi.munitions.WarheadFlag;
import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import com.wariumapi.tag.WariumTagKeys;
import java.util.EnumSet;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    private static boolean registered;

    public ExampleMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::onCommonSetup);

        MinecraftForge.EVENT_BUS.register(new ExampleEvents());
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ExampleMod::registerProfiles);
    }

    @SubscribeEvent
    public void onApiReady(WariumApiReadyEvent event) {
        registerProfiles();
    }

    private static void registerProfiles() {
        if (registered) {
            return;
        }
        registered = true;

        ArmorService armor = WariumApi.get().armor();
        ResourceLocation armorId = new ResourceLocation(MOD_ID, "ceramic_armor");
        ArmorProfile ceramic = new ArmorProfile(14.0, 9.0, 4.0, 0.15, 0.25, 0.2, Map.of());
        armor.registerProfile(armorId, ceramic);
        armor.bindProfileItemTag(armorId, WariumTagKeys.Items.ARMOR_MATERIALS);

        WariumApi.get().ballistics().registerProfile(
                new ResourceLocation(MOD_ID, "ap_round"),
                new ProjectileProfile(10.0, 0.12, 800.0, EnumSet.of(ProjectileFlag.AP), 0.0,
                        FuseType.NONE, 1.0, 1.0, 0.1)
        );

        WariumApi.get().munitions().ifPresent(munitions -> {
            ResourceLocation warheadId = new ResourceLocation(MOD_ID, "he_warhead");
            WarheadProfile warhead = new WarheadProfile(4.0, 1.0, 0.5,
                    EnumSet.noneOf(WarheadFlag.class), Map.of());

            ResourceLocation munitionId = new ResourceLocation(MOD_ID, "simple_missile");
            MunitionProfile munition = new MunitionProfile(
                    MunitionKind.MISSILE, 40.0, 0.02, warheadId,
                    EnumSet.noneOf(MunitionFlag.class), Map.of()
            );

            munitions.registerWarhead(warheadId, warhead);
            munitions.registerMunition(munitionId, munition);
        });
    }
}
