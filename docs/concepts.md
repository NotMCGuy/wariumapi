# Concepts

## API philosophy
Expose contracts, not internals. The API provides stable Java types and interfaces so addon mods can integrate without patching Warium or WariumVS.

## Service access
`WariumApi.get()` is always available. Some services are optional and return `Optional.empty()` when their backing mod is not installed. This allows addons to be loaded safely even when Warium or WariumVS is missing.

## Data-first design
Most integrations are based on immutable profiles (armor, projectiles, munitions, ship parts) registered under `ResourceLocation` IDs, then bound to items, blocks, entities, or tags.

## Event-driven behavior
Runtime integration happens through Forge events (projectile impacts, process lifecycle, wrench actions, control input, munition launches and detonations). All events are server-side.

## Stability
Changes are conservative and additive where possible. The API may lag behind Warium and WariumVS changes, so verify behavior when updating.
