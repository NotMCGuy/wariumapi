package com.wariumapi.event;

import com.wariumapi.radiation.IRadiationService;
import com.wariumapi.radiation.RadiationServices;
import net.minecraftforge.eventbus.api.Event;

public class RadiationServiceRegisterEvent extends Event {
    public boolean register(IRadiationService service, String ownerModId, int priority) {
        return RadiationServices.set(service, ownerModId, priority);
    }
}

