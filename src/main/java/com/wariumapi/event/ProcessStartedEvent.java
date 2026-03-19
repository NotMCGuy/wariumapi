package com.wariumapi.event;

import com.wariumapi.process.ProcessContext;
import net.minecraftforge.eventbus.api.Event;


public class ProcessStartedEvent extends Event {
    private final ProcessContext context;

    public ProcessStartedEvent(ProcessContext context) {
        this.context = context;
    }

    public ProcessContext getContext() {
        return context;
    }
}

