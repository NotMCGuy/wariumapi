package com.wariumapi.event;

import com.wariumapi.tool.WrenchContext;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;


@Cancelable
public class WrenchUseEvent extends Event {
    private final WrenchContext context;

    public WrenchUseEvent(WrenchContext context) {
        this.context = context;
    }

    public WrenchContext getContext() {
        return context;
    }
}