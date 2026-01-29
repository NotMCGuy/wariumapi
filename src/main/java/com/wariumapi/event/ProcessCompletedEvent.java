package com.wariumapi.event;

import com.wariumapi.process.ProcessContext;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired after a Warium process completes its output.
 */
public class ProcessCompletedEvent extends Event {
    private final ProcessContext context;

    public ProcessCompletedEvent(ProcessContext context) {
        this.context = context;
    }

    public ProcessContext getContext() {
        return context;
    }
}

