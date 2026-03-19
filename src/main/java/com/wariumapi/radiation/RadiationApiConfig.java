package com.wariumapi.radiation;

import net.minecraftforge.common.ForgeConfigSpec;

public final class RadiationApiConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLE_DEFAULT_PROVIDER;
    public static final ForgeConfigSpec.BooleanValue ALLOW_PROVIDER_OVERRIDE;
    public static final ForgeConfigSpec.BooleanValue LOG_PROVIDER_SELECTION;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("radiation");
        ENABLE_DEFAULT_PROVIDER = builder
                .comment("Enable registration/usage of Warium default radiation provider.")
                .define("enableDefaultProvider", true);
        ALLOW_PROVIDER_OVERRIDE = builder
                .comment("Allow higher-priority external mods to replace current radiation provider.")
                .define("allowProviderOverride", true);
        LOG_PROVIDER_SELECTION = builder
                .comment("Log provider selection and replacement decisions.")
                .define("logProviderSelection", true);
        builder.pop();
        SPEC = builder.build();
    }

    private RadiationApiConfig() {
    }
}

