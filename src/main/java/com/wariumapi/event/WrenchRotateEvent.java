package com.wariumapi.event;

import com.wariumapi.tool.WrenchContext;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired when a wrench action rotates a block.
 */
public class WrenchRotateEvent extends Event {
    private final WrenchContext context;

    public WrenchRotateEvent(WrenchContext context) {
        this.context = context;
    }

    public WrenchContext getContext() {
        return context;
    }
}