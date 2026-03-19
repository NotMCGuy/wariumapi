package com.wariumapi.event;

import com.wariumapi.radiation.RadiationContext;
import com.wariumapi.radiation.RadiationReading;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class RadiationApplyEvent extends Event {
    private final Entity entity;
    private final RadiationContext context;
    private final RadiationReading reading;

    public RadiationApplyEvent(Entity entity, RadiationContext context, RadiationReading reading) {
        this.entity = entity;
        this.context = context;
        this.reading = reading;
    }

    public Entity getEntity() {
        return entity;
    }

    public RadiationContext getContext() {
        return context;
    }

    public RadiationReading getReading() {
        return reading;
    }
}

