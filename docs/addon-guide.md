# Addon Guide

This guide explains how to integrate with Warium safely using the API.

## 1) Depend on the API only
- Use `warium-api` as `compileOnly` and the runtime mod as `runtimeOnly`.
- Do not compile against Warium or WariumVS internals. The API is the supported surface.

## 2) Access services safely
`WariumApi.get()` always returns a valid instance, but some services are optional.

```java
WariumApi api = WariumApi.get();
api.armor();
api.ballistics();
api.process().ifPresent(process -> {});
api.munitions().ifPresent(munitions -> {});
api.tools().ifPresent(tools -> {});
api.vehicle().ifPresent(vehicle -> {});
api.vs().ifPresent(vs -> {});
```

If Warium or WariumVS is not installed, optional services are empty and no-op services are used.

## 3) Registration timing
- Register profiles during common setup.
- If you cannot rely on ordering, register after `WariumApiReadyEvent`.
- Prefer `event.enqueueWork` when registering from mod setup events.

## 4) Use unique IDs
Always use your mod ID in `ResourceLocation`s to avoid collisions. If two mods register the same ID, the last registration usually wins.

## 5) Bind to tags when possible
Tag bindings let pack makers and addon authors extend behavior without code.
- Armor: item and block tags
- Munitions: item tags
- WariumVS: item and block tags

See `WariumTagKeys` for the built-in tag names.

## 6) Event handling
All API events are fired on the server thread. Keep handlers fast and avoid client-only code.

## 7) WariumVS notes
- `vehicle()` exposes existing control nodes only. You cannot create new nodes via the API.
- `vs()` exposes ship part registration and `ShipController` for applying forces.

## 8) Tools / wrench
- Register custom wrench-like tools via `ToolService`.
- Cancel `WrenchUseEvent` to block the default action.

## 9) Munitions
- Register warhead and munition profiles.
- Listen to `MunitionLaunchedEvent` and `MunitionDetonateEvent` for runtime behavior.
