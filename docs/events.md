# Events

All events are Forge events and fire on the server thread.

## WariumApiReadyEvent
Fired after Warium registers its core API services. Use this if you cannot rely on common setup ordering for profile registration.

## ProjectileImpactEvent
Fired when a Warium projectile resolves an impact. You can mutate `ImpactResult` to adjust damage, penetration depth, ricochet, spall, or explosion triggers.

## ProcessStartedEvent
Fired before a Warium process completes its output. Provides `ProcessContext` with level, position, and recipe.

## ProcessCompletedEvent
Fired after a Warium process completes its output. Provides `ProcessContext` with level, position, and recipe.

## MunitionLaunchedEvent
Fired when a munition entity is launched or spawned. Provides munition and warhead profiles.

## MunitionDetonateEvent
Cancellable. Fired when a munition detonates. Cancel to suppress the default detonation.

## VehicleControlInputEvent
Fired after WariumVS applies control input to a vehicle control node. Includes the node, its position, and current `ControlState`.

## VehicleControlNodeActivatedEvent
Fired when a vehicle control node is linked or activated. Includes the player who activated it.

## VehicleControlNodeDeactivatedEvent
Fired when a vehicle control node is unlinked or deactivated. Includes the player who deactivated it.

## WrenchUseEvent
Cancellable. Fired when a wrench-like tool is used on a block. Use this to prevent the default wrench behavior.

## WrenchRotateEvent
Fired when a wrench action rotates a block.

## WrenchDismantleEvent
Fired when a wrench action dismantles a block.

Note: `WrenchContext` may have null face/hand values depending on the caller.
