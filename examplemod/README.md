# Warium API Example Mod

This folder contains a small Forge mod that demonstrates how to use the Warium API.
It registers a few profiles on startup and listens for events.

## What it shows
- Accessing `WariumApi` services
- Registering armor, ballistics, and munition profiles
- Listening to `ProjectileImpactEvent` and `WrenchUseEvent`
- Optional registration on `WariumApiReadyEvent`

## How to use it
- Treat this as a template you can copy into your own mod.
- Add or remove profile registrations to match your content.
- Replace the `examplemod` mod ID with your own.

## Running (from repo root)
If you include this as a Gradle subproject, you can run:

```
./gradlew -PincludeMods=true :examplemod:runClient
```
