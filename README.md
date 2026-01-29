# Warium API

**Warium API** is the official modding and integration API for **Warium** and **WariumVS**.

It provides a stable, Java-facing contract that allows third-party mods to integrate with Warium systems without depending on internal implementation details. The API is designed to support long-term compatibility, clean extension points, and data-driven integration.

---

## What this is

- A **stable modding API** for Warium and WariumVS  
- A supported way to **add custom parts, materials, profiles, and behaviours**  
- A safe integration layer for damage, ballistics, machines, and ship systems  
- The foundation used by **Warium Integrations** (KubeJS support)

---

## What this is *not*

- ❌ The Warium mod itself  
- ❌ A content mod (no blocks, items, or entities)  
- ❌ A scripting-only API  
- ❌ A physics engine or full simulator  

If you are just playing Warium, you do **not** need this mod.  
If you are writing an addon or integration, this is what you should depend on.

---

## Supported mods

- **Warium** – required at runtime  
- **WariumVS** – optional (adds ship and vehicle integration hooks)

The API loads safely even if WariumVS is not installed.

---

## Installation

### For players / modpacks
You generally do **not** need to install this manually.  
It is included automatically when required by addon mods or integrations.

### For developers (Gradle)

```gradle
repositories {
    mavenCentral()
    // add additional maven repositories here if required
}

dependencies {
    compileOnly "com.yourgroup:warium-api:<version>"
    runtimeOnly "com.yourgroup:warium:<version>"
}
