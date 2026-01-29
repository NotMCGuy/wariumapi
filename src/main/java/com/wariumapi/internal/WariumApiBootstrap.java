package com.wariumapi.internal;

import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.process.ProcessService;
import com.wariumapi.tool.ToolService;
import com.wariumapi.vehicle.VehicleService;
import com.wariumapi.vs.VsService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Internal bootstrap for Warium/WariumVS to register service implementations.
 *
 * <p>Third-party mods should not call these methods.</p>
 */
public final class WariumApiBootstrap {
    private static final AtomicBoolean CORE_SET = new AtomicBoolean(false);

    private WariumApiBootstrap() {
    }

    /**
     * Register core Warium services. First call wins.
     */
    public static void registerCoreServices(ArmorService armor, BallisticsService ballistics, ProcessService process) {
        if (CORE_SET.compareAndSet(false, true)) {
            ServiceRegistry.setArmorService(armor);
            ServiceRegistry.setBallisticsService(ballistics);
            if (process != null) {
                ServiceRegistry.setProcessService(process);
            }
        }
    }

    /**
     * Register Warium munitions service. Last call wins.
     */
    public static void registerMunitionsService(MunitionsService munitionsService) {
        ServiceRegistry.setMunitionsService(munitionsService);
    }

    /**
     * Register Warium tool service. Last call wins.
     */
    public static void registerToolService(ToolService toolService) {
        ServiceRegistry.setToolService(toolService);
    }

    /**
     * Register WariumVS vehicle service. Last call wins.
     */
    public static void registerVehicleService(VehicleService vehicleService) {
        ServiceRegistry.setVehicleService(vehicleService);
    }

    /**
     * Register WariumVS service. Last call wins.
     */
    public static void registerVsService(VsService vsService) {
        ServiceRegistry.setVsService(vsService);
    }
}

