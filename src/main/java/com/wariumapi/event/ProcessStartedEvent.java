package com.wariumapi.event;

import com.wariumapi.process.ProcessContext;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired before a Warium process completes its output.
 */
public class ProcessStartedEvent extends Event {
    private final ProcessContext context;

    public ProcessStartedEvent(ProcessContext context) {
        this.context = context;
    }

    public ProcessContext getContext() {
        return context;
    }
}

