package com.wariumapi.event;

import com.wariumapi.tool.WrenchContext;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired when a wrench action dismantles a block.
 */
public class WrenchDismantleEvent extends Event {
    private final WrenchContext context;

    public WrenchDismantleEvent(WrenchContext context) {
        this.context = context;
    }

    public WrenchContext getContext() {
        return context;
    }
}