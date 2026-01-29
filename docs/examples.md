# Examples

The snippets below demonstrate common Warium API usage patterns. Adjust IDs and values to suit your mod.

## Register an armor profile and bind it to a tag

```java
import com.wariumapi.WariumApi;
import com.wariumapi.armor.ArmorProfile;
import com.wariumapi.armor.ArmorService;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

ArmorService armor = WariumApi.get().armor();
ArmorProfile ceramic = new ArmorProfile(14.0, 9.0, 4.0, 0.15, 0.25, 0.2, java.util.Map.of());
ResourceLocation id = new ResourceLocation("my_mod", "ceramic_armor");

armor.registerProfile(id, ceramic);
armor.bindProfileItemTag(id, ItemTags.create(new ResourceLocation("my_mod", "ceramic_armor")));
```

## Register a projectile profile and adjust impacts

```java
import com.wariumapi.WariumApi;
import com.wariumapi.ballistics.ProjectileProfile;
import com.wariumapi.ballistics.ProjectileFlag;
import com.wariumapi.event.ProjectileImpactEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;

WariumApi.get().ballistics().registerProfile(
        new ResourceLocation("my_mod", "ap_round"),
        new ProjectileProfile(10.0, 0.12, 800.0, java.util.EnumSet.of(ProjectileFlag.AP), 0.0,
                com.wariumapi.ballistics.FuseType.NONE, 1.0, 1.0, 0.1)
);

public class MyEvents {
    @SubscribeEvent
    public void onImpact(ProjectileImpactEvent event) {
        event.getImpactResult().setDamageMultiplier(0.85);
        event.getImpactResult().setRicochet(false);
    }
}
```

## Register a warhead and munition

```java
import com.wariumapi.WariumApi;
import com.wariumapi.munitions.MunitionKind;
import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import net.minecraft.resources.ResourceLocation;

WariumApi.get().munitions().ifPresent(munitions -> {
    ResourceLocation warheadId = new ResourceLocation("my_mod", "he_warhead");
    WarheadProfile warhead = new WarheadProfile(4.0, 1.0, 0.5, java.util.EnumSet.noneOf(com.wariumapi.munitions.WarheadFlag.class), java.util.Map.of());

    ResourceLocation munitionId = new ResourceLocation("my_mod", "simple_missile");
    MunitionProfile munition = new MunitionProfile(MunitionKind.MISSILE, 40.0, 0.02, warheadId,
            java.util.EnumSet.noneOf(com.wariumapi.munitions.MunitionFlag.class), java.util.Map.of());

    munitions.registerWarhead(warheadId, warhead);
    munitions.registerMunition(munitionId, munition);
});
```

## Register a custom wrench tool

```java
import com.wariumapi.WariumApi;
import net.minecraft.world.level.ItemLike;

ItemLike myWrench = /* your wrench item */ null;

WariumApi.get().tools().ifPresent(tools -> tools.registerWrench(myWrench));
```

## Read vehicle control input (WariumVS)

```java
import com.wariumapi.WariumApi;
import com.wariumapi.event.VehicleControlInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VehicleEvents {
    @SubscribeEvent
    public void onInput(VehicleControlInputEvent event) {
        float yaw = event.getState().yaw();
        float pitch = event.getState().pitch();
        boolean landingGearDown = event.getState().flags().contains(com.wariumapi.vehicle.ControlFlag.LANDING_GEAR);
        // respond to input
    }
}
```
