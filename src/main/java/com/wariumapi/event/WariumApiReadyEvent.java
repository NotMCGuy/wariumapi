package com.wariumapi.event;

import net.minecraftforge.eventbus.api.Event;

/**
 * Fired after Warium registers its core API services.
 *
 * <p>Use this to register profiles if you cannot rely on common setup ordering.</p>
 */
public class WariumApiReadyEvent extends Event {
}
