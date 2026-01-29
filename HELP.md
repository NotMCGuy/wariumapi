# Warium API Help

This repository is the **API only**. It provides Java interfaces, records, and events that other mods can compile against without depending on Warium/WariumVS implementation internals.

## What the API does
- Exposes **stable contracts** for armor, ballistics, machine processes, and (optionally) ship integration.
- Provides **events** so other mods can react to Warium behavior without patching or rewriting internals.
- Keeps the API **server-safe** (no client-only classes).

## What the API does not do
- It is **not** the Warium gameplay mod itself.
- It does **not** implement gameplay logic.
- It does **not** depend on optional mods (KubeJS, JEI, Create, Valkyrien Skies, etc.).

## How it works (high level)
1. **Warium** loads and registers its internal implementations using `WariumApiBootstrap`.
2. Third-party mods call `WariumApi.get()` to access services.
3. Those services expose profile registries and query functions.
4. Warium fires API events at runtime (e.g., projectile impacts, process completion).

If Warium is **not** installed, API services return safe no-op implementations so mods won’t crash.

## Core entrypoint
- `com.wariumapi.WariumApi`

```java
WariumApi api = WariumApi.get();
```

Services:
- `api.armor()` → Armor profiles and lookups
- `api.ballistics()` → Projectile profiles and lookups
- `api.process()` → Optional process/recipe access (only if Warium provides it)
- `api.vs()` → Optional ship integration (only if WariumVS is present)

## Armor API
Records:
- `ArmorProfile` (thickness, hardness, toughness, spall, ricochet, blast, extras)

Service:
- `ArmorService`
  - `registerProfile(ResourceLocation id, ArmorProfile profile)`
  - `bindProfile(ResourceLocation id, Item/Block/TagKey)`
  - `getProfile(ItemStack/BlockState)`

Example:
```java
ArmorService armor = WariumApi.get().armor();
ArmorProfile ceramic = new ArmorProfile(14.0, 9.0, 4.0, 0.15, 0.25, 0.2, Map.of());
ResourceLocation id = new ResourceLocation("my_mod", "ceramic_armor");
armor.registerProfile(id, ceramic);
armor.bindProfile(id, ItemTags.create(new ResourceLocation("my_mod", "ceramic_armor")));
```

## Ballistics API
Records:
- `ProjectileProfile` (mass, calibre, velocity, flags, penetration, damageScale, etc.)

Event:
- `ProjectileImpactEvent`
  - Lets you adjust `damageMultiplier`, `penetrationDepth`, or force `ricochet`.

Example event handler:
```java
@SubscribeEvent
public void onImpact(ProjectileImpactEvent event) {
    event.getResult().setDamageMultiplier(event.getResult().damageMultiplier() * 0.9);
}
```

## Process API (optional)
- `ProcessService` provides read-only access to Warium’s data-driven machine recipes.
- `ProcessStartedEvent` and `ProcessCompletedEvent` fire when assembly outputs are produced.

Example:
```java
WariumApi.get().process().ifPresent(process -> {
    for (ProcessRecipe recipe : process.getRecipes(new ResourceLocation("crusty_chunks", "assembly"))) {
        // inspect recipe
    }
});
```

## VS Integration (optional)
If WariumVS is installed, `api.vs()` will be present.
It provides:
- `ShipPartProfile` registration + binding
- `ShipController` for applying forces (no direct Valkyrien Skies types in API)

## Dependencies (modders)
Gradle (Groovy):
```groovy
repositories {
    maven { url 'https://maven.minecraftforge.net/' }
    // add your Maven repository for published Warium artifacts
}

dependencies {
    compileOnly "com.wariumapi:warium-api:<api_version>"
    runtimeOnly "com.wariumapi:warium:<warium_version>"
    // runtimeOnly "com.wariumapi:wariumvs:<wariumvs_version>" // optional
}
```

## Example mod
See `examplemod` for a working Forge example that registers profiles and listens for impact events.
