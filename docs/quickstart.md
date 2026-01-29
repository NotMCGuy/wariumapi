# Quickstart

This guide gets you integrating with Warium API quickly. It assumes Forge on Minecraft 1.20.1.

---

## Add the dependency

Add your Maven repository where Warium artifacts are published, then depend on the API and the runtime mod.

```gradle
repositories {
    mavenCentral()
    maven { url "https://maven.minecraftforge.net" }
    // TODO: add the Maven repo that hosts Warium artifacts
}

dependencies {
    compileOnly "com.wariumapi:warium-api:<api_version>"
    runtimeOnly "com.wariumapi:warium:<warium_version>"
    // runtimeOnly "com.wariumapi:wariumvs:<wariumvs_version>" // optional
}
```

---

## Access the API

```java
import com.wariumapi.WariumApi;

WariumApi api = WariumApi.get();

// Always present
api.armor();
api.ballistics();

// Optional services
api.process().ifPresent(process -> {
    // read-only process recipes
});

api.munitions().ifPresent(munitions -> {
    // register or query munitions
});

api.tools().ifPresent(tools -> {
    // register wrench-like tools
});

api.vehicle().ifPresent(vehicle -> {
    // read WariumVS control nodes
});

api.vs().ifPresent(vs -> {
    // register ship parts or apply forces
});
```

---

## Register profiles

Register profiles during common setup, or after `WariumApiReadyEvent` if you cannot guarantee order.

```java
import com.wariumapi.WariumApi;
import com.wariumapi.armor.ArmorProfile;
import com.wariumapi.armor.ArmorService;
import net.minecraft.resources.ResourceLocation;

ArmorService armor = WariumApi.get().armor();
ArmorProfile ceramic = new ArmorProfile(
        14.0, 9.0, 4.0, 0.15, 0.25, 0.2, java.util.Map.of()
);
ResourceLocation id = new ResourceLocation("my_mod", "ceramic_armor");

armor.registerProfile(id, ceramic);
```

---

## Listen to events

All API events are fired server-side on the Forge event bus.

```java
import com.wariumapi.event.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MyEvents {
    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        // adjust impact results before Warium applies them
        event.getImpactResult().setDamageMultiplier(0.9);
    }
}
```

If you need to do late registration, listen for `WariumApiReadyEvent` and register there.
