package com.wariumapi.registry;

import com.wariumapi.armor.ArmorProfile;
import com.wariumapi.ballistics.ProjectileProfile;
import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import com.wariumapi.process.ProcessRecipe;
import com.wariumapi.vs.ShipPartProfile;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

/**
 * Registry keys for Warium API data types.
 */
public final class WariumRegistryKeys {
    public static final ResourceKey<Registry<ArmorProfile>> ARMOR_PROFILES =
            ResourceKey.createRegistryKey(new ResourceLocation("warium", "armor_profiles"));

    public static final ResourceKey<Registry<ProjectileProfile>> PROJECTILE_PROFILES =
            ResourceKey.createRegistryKey(new ResourceLocation("warium", "projectile_profiles"));

    public static final ResourceKey<Registry<ProcessRecipe>> PROCESS_RECIPES =
            ResourceKey.createRegistryKey(new ResourceLocation("warium", "process_recipes"));

    public static final ResourceKey<Registry<ShipPartProfile>> SHIP_PARTS =
            ResourceKey.createRegistryKey(new ResourceLocation("warium", "ship_parts"));

    public static final ResourceKey<Registry<MunitionProfile>> MUNITION_PROFILES =
            ResourceKey.createRegistryKey(new ResourceLocation("warium", "munition_profiles"));

    public static final ResourceKey<Registry<WarheadProfile>> WARHEAD_PROFILES =
            ResourceKey.createRegistryKey(new ResourceLocation("warium", "warhead_profiles"));

    private WariumRegistryKeys() {
    }
}

