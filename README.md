# Warium API

⚠️ **In development / work in progress** ⚠️

Warium API is a Java-facing modding API for **Warium** and **WariumVS**.
It exposes stable contracts, registries, services, and Forge events so addon mods can integrate with existing Warium systems **without touching internal code**.

This project is developed and maintained **by MC**, not by **Novum**.

Because of this:
- the API may **lag behind** changes in Warium/WariumVS
- updates may require **manual review and adjustment**
- the API surface is still being stabilised

If you are just playing Warium, you **do not need this mod**.
If you are writing an addon or integration, this is the correct dependency.

---

## What this is

- A lightweight **API mod** (`warium-api`)
- A supported integration surface for Warium and WariumVS
- Server-safe by default (no client-only code in the API)

## What this is not

- Not Warium or WariumVS
- Not a gameplay/content mod
- Not a bundle of optional integrations (CC, KubeJS, JEI, etc.)
- Not a guarantee that every internal mechanic is exposed

---

## What the API allows you to do

### Armour & materials
- Register `ArmorProfile`s
- Bind profiles to items, blocks, or tags
- Query resolved armour data

### Ballistics & impacts
- Register `ProjectileProfile`s
- Bind them to projectile entities
- Listen to `ProjectileImpactEvent`
- Modify damage, penetration, and ricochet

### Processing / machines
- Query `ProcessRecipe`s
- Listen to process lifecycle events

### Vehicle control (WariumVS)
- Access existing vehicle control nodes
- Read current yaw / pitch / roll / throttle / landing gear state
- Listen to control input and activation events

### Wrench / tools (WariumVS)
- Identify wrench-like tools
- Listen to wrench use events
- Cancel actions to block underlying behaviour

### Missiles & bombs (Warium)
- Register and query munition and warhead profiles
- Listen to munition launch and detonation events
- Cancel or suppress detonation

---

## Known gaps & limitations

These are intentionally not exposed yet:

- No internal physics / penetration formulas
- No client hooks (rendering, HUD, particles)
- No mid-flight per-tick munition control
- Vehicle API only reflects **existing WariumVS control blocks**
- Tool API is event-first, not pipeline-based
- No networking helpers
- No binary compatibility guarantees yet

---

## Installation (developers)

```gradle
dependencies {
    compileOnly "com.wariumapi:warium-api:<api_version>"
    runtimeOnly "com.wariumapi:warium:<warium_version>"
    // optional:
    // runtimeOnly "com.wariumapi:wariumvs:<wariumvs_version>"
}
