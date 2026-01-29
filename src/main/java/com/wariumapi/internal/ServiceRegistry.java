package com.wariumapi.internal;

import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.process.ProcessService;
import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.tool.ToolService;
import com.wariumapi.vehicle.VehicleService;
import com.wariumapi.vs.VsService;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public final class ServiceRegistry {
    private static final ArmorService NOOP_ARMOR = new NoopArmorService();
    private static final BallisticsService NOOP_BALLISTICS = new NoopBallisticsService();
    private static final ProcessService NOOP_PROCESS = new NoopProcessService();
    private static final MunitionsService NOOP_MUNITIONS = new NoopMunitionsService();
    private static final ToolService NOOP_TOOLS = new NoopToolService();
    private static final VehicleService NOOP_VEHICLE = new NoopVehicleService();
    private static final VsService NOOP_VS = new NoopVsService();

    private static final AtomicReference<ArmorService> ARMOR = new AtomicReference<>(NOOP_ARMOR);
    private static final AtomicReference<BallisticsService> BALLISTICS = new AtomicReference<>(NOOP_BALLISTICS);
    private static final AtomicReference<ProcessService> PROCESS = new AtomicReference<>(NOOP_PROCESS);
    private static final AtomicReference<MunitionsService> MUNITIONS = new AtomicReference<>(NOOP_MUNITIONS);
    private static final AtomicReference<ToolService> TOOLS = new AtomicReference<>(NOOP_TOOLS);
    private static final AtomicReference<VehicleService> VEHICLE = new AtomicReference<>(NOOP_VEHICLE);
    private static final AtomicReference<VsService> VS = new AtomicReference<>(NOOP_VS);

    private ServiceRegistry() {
    }

    public static ArmorService armor() {
        return ARMOR.get();
    }

    public static BallisticsService ballistics() {
        return BALLISTICS.get();
    }

    public static Optional<ProcessService> process() {
        ProcessService service = PROCESS.get();
        return service == NOOP_PROCESS ? Optional.empty() : Optional.of(service);
    }

    public static Optional<MunitionsService> munitions() {
        MunitionsService service = MUNITIONS.get();
        return service == NOOP_MUNITIONS ? Optional.empty() : Optional.of(service);
    }

    public static Optional<ToolService> tools() {
        ToolService service = TOOLS.get();
        return service == NOOP_TOOLS ? Optional.empty() : Optional.of(service);
    }

    public static Optional<VehicleService> vehicle() {
        VehicleService service = VEHICLE.get();
        return service == NOOP_VEHICLE ? Optional.empty() : Optional.of(service);
    }

    public static Optional<VsService> vs() {
        VsService service = VS.get();
        return service == NOOP_VS ? Optional.empty() : Optional.of(service);
    }

    public static void setArmorService(ArmorService service) {
        if (service != null) {
            ARMOR.set(service);
        }
    }

    public static void setBallisticsService(BallisticsService service) {
        if (service != null) {
            BALLISTICS.set(service);
        }
    }

    public static void setProcessService(ProcessService service) {
        if (service != null) {
            PROCESS.set(service);
        }
    }

    public static void setMunitionsService(MunitionsService service) {
        if (service != null) {
            MUNITIONS.set(service);
        }
    }

    public static void setToolService(ToolService service) {
        if (service != null) {
            TOOLS.set(service);
        }
    }

    public static void setVehicleService(VehicleService service) {
        if (service != null) {
            VEHICLE.set(service);
        }
    }

    public static void setVsService(VsService service) {
        if (service != null) {
            VS.set(service);
        }
    }
}

