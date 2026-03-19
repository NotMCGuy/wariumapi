package com.wariumapi.radiation;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RadiationServices {
    private static final Logger LOGGER = LogManager.getLogger("WariumApiRadiation");
    private static final AtomicReference<Entry> ACTIVE = new AtomicReference<>(new Entry(NoopRadiationService.INSTANCE, "wariumapi", Integer.MIN_VALUE));

    private RadiationServices() {
    }

    public static IRadiationService get() {
        return ACTIVE.get().service;
    }

    public static String getOwnerModId() {
        return ACTIVE.get().ownerModId;
    }

    public static int getPriority() {
        return ACTIVE.get().priority;
    }

    public static boolean isOwner(String ownerModId) {
        return Objects.equals(getOwnerModId(), ownerModId);
    }

    
    public static boolean set(IRadiationService service, String ownerModId, int priority) {
        if (service == null || ownerModId == null || ownerModId.isBlank()) {
            return false;
        }
        Entry incoming = new Entry(service, ownerModId, priority);
        while (true) {
            Entry current = ACTIVE.get();
            if (current == incoming) {
                return true;
            }

            boolean sameOwner = current.ownerModId.equals(ownerModId);
            boolean canOverride = RadiationApiConfig.ALLOW_PROVIDER_OVERRIDE.get();
            if (!sameOwner && !canOverride && current.priority > Integer.MIN_VALUE) {
                logSelection("Rejected provider {} (priority {}), overrides disabled; active is {} (priority {})",
                        ownerModId, priority, current.ownerModId, current.priority);
                return false;
            }

            if (!sameOwner && current.priority > priority) {
                logSelection("Rejected provider {} (priority {}), active is {} (priority {})",
                        ownerModId, priority, current.ownerModId, current.priority);
                return false;
            }

            if (ACTIVE.compareAndSet(current, incoming)) {
                logSelection("Selected provider {} (priority {}), replacing {} (priority {})",
                        ownerModId, priority, current.ownerModId, current.priority);
                if ("crusty_chunks".equals(current.ownerModId)
                        && "wariumliquidators".equals(ownerModId)
                        && priority >= current.priority) {
                    LOGGER.info("Liquidators radiation provider is active; default Warium radiation provider is disabled.");
                }
                return true;
            }
        }
    }

    private static void logSelection(String message, Object... args) {
        if (RadiationApiConfig.LOG_PROVIDER_SELECTION.get()) {
            LOGGER.info(message, args);
        }
    }

    private static final class Entry {
        private final IRadiationService service;
        private final String ownerModId;
        private final int priority;

        private Entry(IRadiationService service, String ownerModId, int priority) {
            this.service = service;
            this.ownerModId = ownerModId;
            this.priority = priority;
        }
    }
}
