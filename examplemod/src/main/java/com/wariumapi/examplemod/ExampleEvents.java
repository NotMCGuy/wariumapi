package com.wariumapi.examplemod;

import com.wariumapi.event.ProjectileImpactEvent;
import com.wariumapi.event.WrenchUseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ExampleEvents {
    @SubscribeEvent
    public void onImpact(ProjectileImpactEvent event) {
        event.getImpactResult().setDamageMultiplier(0.9);
    }

    @SubscribeEvent
    public void onWrenchUse(WrenchUseEvent event) {
        if (event.getContext().isSneaking()) {
            event.setCanceled(true);
        }
    }
}
