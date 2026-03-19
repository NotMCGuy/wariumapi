package com.wariumapi.internal;

import com.wariumapi.aimer.AimerService;
import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.effects.ExplosionEffectsService;
import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.process.ProcessService;
import com.wariumapi.radar.RadarService;
import com.wariumapi.tool.ToolService;
import com.wariumapi.vehicle.VehicleService;
import com.wariumapi.vs.VsService;
import java.util.concurrent.atomic.AtomicBoolean;


public final class WariumApiBootstrap {
    private static final AtomicBoolean CORE_SET = new AtomicBoolean(false);

    private WariumApiBootstrap() {
    }

    
    public static void registerCoreServices(ArmorService armor, BallisticsService ballistics, ProcessService process) {
        registerCoreServices(armor, ballistics, process, new AimerServiceImpl());
    }

    
    public static void registerCoreServices(ArmorService armor, BallisticsService ballistics, ProcessService process, AimerService aimer) {
        if (CORE_SET.compareAndSet(false, true)) {
            ServiceRegistry.setArmorService(armor);
            ServiceRegistry.setBallisticsService(ballistics);
            if (process != null) {
                ServiceRegistry.setProcessService(process);
            }
            if (aimer != null) {
                ServiceRegistry.setAimerService(aimer);
            }
        }
    }

    
    public static void registerMunitionsService(MunitionsService munitionsService) {
        ServiceRegistry.setMunitionsService(munitionsService);
    }

    
    public static void registerToolService(ToolService toolService) {
        ServiceRegistry.setToolService(toolService);
    }

    
    public static void registerVehicleService(VehicleService vehicleService) {
        ServiceRegistry.setVehicleService(vehicleService);
    }

    
    public static void registerVsService(VsService vsService) {
        ServiceRegistry.setVsService(vsService);
    }

    
    public static void registerAimerService(AimerService aimerService) {
        ServiceRegistry.setAimerService(aimerService);
    }

    
    public static void registerRadarService(RadarService radarService) {
        ServiceRegistry.setRadarService(radarService);
    }

    
    public static void registerEffectsService(ExplosionEffectsService effectsService) {
        ServiceRegistry.setEffectsService(effectsService);
    }
}

