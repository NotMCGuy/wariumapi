package com.wariumapi.event;

import com.wariumapi.ballistics.ImpactContext;
import com.wariumapi.ballistics.ImpactResult;
import net.minecraftforge.eventbus.api.Event;


public class ProjectileImpactEvent extends Event {
    private final ImpactContext context;
    private final ImpactResult result;

    public ProjectileImpactEvent(ImpactContext context, ImpactResult result) {
        this.context = context;
        this.result = result;
    }

    public ImpactContext getContext() {
        return context;
    }

    public ImpactResult getImpactResult() {
        return result;
    }
}

