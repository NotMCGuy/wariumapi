# Import Reference

This page lists the common Java imports you will use when working with the Warium API. Use it as a quick copy/paste reference.

---

## Core entrypoint

```java
import com.wariumapi.WariumApi;
```

---

## Services

```java
import com.wariumapi.armor.ArmorService;
import com.wariumapi.ballistics.BallisticsService;
import com.wariumapi.munitions.MunitionsService;
import com.wariumapi.process.ProcessService;
import com.wariumapi.tool.ToolService;
import com.wariumapi.vehicle.VehicleService;
import com.wariumapi.vs.VsService;
```

---

## Profiles / data records

```java
import com.wariumapi.armor.ArmorProfile;
import com.wariumapi.ballistics.ProjectileProfile;
import com.wariumapi.munitions.profile.MunitionProfile;
import com.wariumapi.munitions.profile.WarheadProfile;
import com.wariumapi.process.ProcessRecipe;
import com.wariumapi.vs.ShipPartProfile;
import com.wariumapi.vehicle.ControlState;
```

---

## Enums / flags

```java
import com.wariumapi.ballistics.FuseType;
import com.wariumapi.ballistics.ProjectileFlag;
import com.wariumapi.munitions.MunitionFlag;
import com.wariumapi.munitions.MunitionKind;
import com.wariumapi.munitions.WarheadFlag;
import com.wariumapi.vehicle.ControlFlag;
import com.wariumapi.vs.ForceDirectionMode;
import com.wariumapi.vs.ForceMode;
```

---

## Events

```java
import com.wariumapi.event.WariumApiReadyEvent;
import com.wariumapi.event.ProjectileImpactEvent;
import com.wariumapi.event.ProcessStartedEvent;
import com.wariumapi.event.ProcessCompletedEvent;
import com.wariumapi.event.MunitionLaunchedEvent;
import com.wariumapi.event.MunitionDetonateEvent;
import com.wariumapi.event.VehicleControlInputEvent;
import com.wariumapi.event.VehicleControlNodeActivatedEvent;
import com.wariumapi.event.VehicleControlNodeDeactivatedEvent;
import com.wariumapi.event.WrenchUseEvent;
import com.wariumapi.event.WrenchRotateEvent;
import com.wariumapi.event.WrenchDismantleEvent;
```

Forge event bus imports used by listeners:

```java
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
```

---

## Tags and registry keys

```java
import com.wariumapi.tag.WariumTagKeys;
import com.wariumapi.registry.WariumRegistryKeys;
```

---

## Common Minecraft/Forge types used with the API

```java
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
```

---

## Mod entrypoint (Forge)

```java
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
```
