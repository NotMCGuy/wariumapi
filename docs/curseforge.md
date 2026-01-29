# Warium API

Warium API is a lightweight API mod for Warium and WariumVS. It exposes stable Java contracts, registries, and Forge events so addon mods can integrate with Warium systems without touching internal code.

## Who should install this
- Mod developers building Warium or WariumVS integrations.
- Players only need this if a dependent addon requires it.

## Features
- Armor and ballistics profile registries
- Munition and warhead profiles
- Process recipe access
- Wrench and tool events
- WariumVS vehicle and ship integration (when WariumVS is installed)

## Requirements
- Minecraft 1.20.1
- Forge 47.x

## Notes
- This mod contains no gameplay content by itself.
- If Warium is not installed, optional services return empty and the API is safe to load.
- If you are just playing Warium, you usually do not need this mod.
