package com.wariumapi.event;

import com.wariumapi.radiation.RadiationContext;
import com.wariumapi.radiation.RadiationReading;
import net.minecraftforge.eventbus.api.Event;

public class RadiationSampleEvent extends Event {
    private final RadiationContext context;
    private final RadiationReading reading;

    public RadiationSampleEvent(RadiationContext context, RadiationReading reading) {
        this.context = context;
        this.reading = reading;
    }

    public RadiationContext getContext() {
        return context;
    }

    public RadiationReading getReading() {
        return reading;
    }
}

