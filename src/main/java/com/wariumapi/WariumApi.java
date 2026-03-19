package com.wariumapi;

import com.wariumapi.aimer.AimerService;
import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.effects.ExplosionEffectsService;
import com.wariumapi.internal.ServiceRegistry;
import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.process.ProcessService;
import com.wariumapi.radar.RadarService;
import com.wariumapi.radiation.IRadiationService;
import com.wariumapi.radiation.RadiationServices;
import com.wariumapi.tool.ToolService;
import com.wariumapi.vehicle.VehicleService;
import com.wariumapi.vs.VsService;
import java.util.Optional;


public final class WariumApi {
    private static final WariumApi INSTANCE = new WariumApi();

    private WariumApi() {
    }

    
    public static WariumApi get() {
        return INSTANCE;
    }

    
    public ArmorService armor() {
        return ServiceRegistry.armor();
    }

    
    public BallisticsService ballistics() {
        return ServiceRegistry.ballistics();
    }

    
    public Optional<ProcessService> process() {
        return ServiceRegistry.process();
    }

    
    public Optional<MunitionsService> munitions() {
        return ServiceRegistry.munitions();
    }

    
    public IRadiationService radiation() {
        return RadiationServices.get();
    }

    
    public Optional<ToolService> tools() {
        return ServiceRegistry.tools();
    }

    
    public Optional<VehicleService> vehicle() {
        return ServiceRegistry.vehicle();
    }

    
    public Optional<VsService> vs() {
        return ServiceRegistry.vs();
    }

    
    public Optional<AimerService> aimer() {
        return ServiceRegistry.aimer();
    }

    
    public Optional<RadarService> radar() {
        return ServiceRegistry.radar();
    }

    
    public Optional<ExplosionEffectsService> effects() {
        return ServiceRegistry.effects();
    }
}

