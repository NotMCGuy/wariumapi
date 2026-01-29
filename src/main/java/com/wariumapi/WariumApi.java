package com.wariumapi;

import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.internal.ServiceRegistry;
import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.process.ProcessService;
import com.wariumapi.tool.ToolService;
import com.wariumapi.vehicle.VehicleService;
import com.wariumapi.vs.VsService;
import java.util.Optional;

/**
 * Entrypoint for the Warium API.
 *
 * <p>Use {@link #get()} to access services. Services return safe no-op
 * implementations when Warium/WariumVS are not installed.
 */
public final class WariumApi {
    private static final WariumApi INSTANCE = new WariumApi();

    private WariumApi() {
    }

    /**
     * @return the singleton API entrypoint
     */
    public static WariumApi get() {
        return INSTANCE;
    }

    /**
     * @return armor profile service (never null)
     */
    public ArmorService armor() {
        return ServiceRegistry.armor();
    }

    /**
     * @return ballistics service (never null)
     */
    public BallisticsService ballistics() {
        return ServiceRegistry.ballistics();
    }

    /**
     * @return optional process service (empty if not available)
     */
    public Optional<ProcessService> process() {
        return ServiceRegistry.process();
    }

    /**
     * @return optional munitions service (empty if not available)
     */
    public Optional<MunitionsService> munitions() {
        return ServiceRegistry.munitions();
    }

    /**
     * @return optional tool service (empty if not available)
     */
    public Optional<ToolService> tools() {
        return ServiceRegistry.tools();
    }

    /**
     * @return optional vehicle service (empty if WariumVS not installed)
     */
    public Optional<VehicleService> vehicle() {
        return ServiceRegistry.vehicle();
    }

    /**
     * @return optional WariumVS integration service (empty if WariumVS not installed)
     */
    public Optional<VsService> vs() {
        return ServiceRegistry.vs();
    }
}

