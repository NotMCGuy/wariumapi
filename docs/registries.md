# Registries

Warium API uses profile registries and bindings to map items, blocks, entities, and tags to Warium behavior.

## Registry keys
These keys are exposed in `WariumRegistryKeys`:
- `warium:armor_profiles` -> `ArmorProfile`
- `warium:projectile_profiles` -> `ProjectileProfile`
- `warium:process_recipes` -> `ProcessRecipe` (read-only)
- `warium:ship_parts` -> `ShipPartProfile`
- `warium:munition_profiles` -> `MunitionProfile`
- `warium:warhead_profiles` -> `WarheadProfile`

## Armor
- Register profiles with `ArmorService.registerProfile`.
- Bind to items, blocks, or tags with `bindProfile`, `bindProfileItemTag`, and `bindProfileBlockTag`.
- Query by ID, `ItemStack`, or `BlockState`.

## Ballistics
- Register profiles with `BallisticsService.registerProfile`.
- Bind to `EntityType` or projectile classes with `bindProfile`.
- Query by ID or projectile entity.

## Munitions
- Register with `MunitionsService.registerWarhead` and `registerMunition`.
- Bind munition profiles to entities or items with `bindMunition` or `bindMunitionItemTag`.
- Query by ID, entity, or item stack.

## WariumVS ship parts
- Register with `VsService.registerShipPart`.
- Bind to items, blocks, or tags with `bindShipPart*` methods.
- Query by ID, item stack, or block state.

## Process recipes (read-only)
`ProcessService` exposes data-driven machine recipes. There is no public registration API for process recipes.

## Conflict rules
Use unique `ResourceLocation` IDs (prefix with your mod ID). If two registrations use the same ID, the last registration typically wins in the implementation.

## Built-in tags
`WariumTagKeys` exposes common tags such as armor materials and ship parts. Prefer tags over hard-coded item lists when possible.
