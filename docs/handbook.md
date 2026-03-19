# Warium API Handbook (expanded single-file edition, because apparently we like peace)

Welcome to the one document that replaced a pile of smaller ones. If you miss hunting through seventeen markdown files for one line of truth, that’s between you and your therapist.

This handbook is for:
- Addon devs integrating with Warium/WariumVS.
- Server operators trying to keep TPS above “embarrassing”.
- Maintainers who are very tired and would like fewer support tickets.

---

## 1) Quickstart (the bit everyone pretends to read first)

### 1.1 Dependencies
```groovy
dependencies {
    compileOnly "com.wariumapi:warium-api:1.0.5"
    runtimeOnly "com.wariumapi:warium:1.2.7"
    // optional runtime:
    // runtimeOnly "com.wariumapi:wariumvs:1.0.1"
}
```

### 1.2 First registration
```java
ArmorService armor = WariumApi.get().armor();
ArmorProfile profile = new ArmorProfile(14.0, 9.0, 4.0, 0.15, 0.25, 0.2, Map.of());
armor.registerProfile(new ResourceLocation("my_mod", "ceramic_armor"), profile);
```

### 1.3 First event hook
```java
@SubscribeEvent
public void onImpact(ProjectileImpactEvent event) {
    event.getResult().setDamageMultiplier(event.getResult().damageMultiplier() * 0.9);
}
```

### 1.4 Safe optional-service pattern
```java
WariumApi.get().radar().ifPresent(radar -> {
    radar.getProfile(state).ifPresent(profile -> {
        // do useful things instead of null-pointer cosplay
    });
});
```

---

## 2) How the API is meant to be used (and how not to ruin your weekend)

### 2.1 Design principles
- Keep contracts stable and additive.
- Register profiles once, bind many targets.
- Query through services; do not poke internals.
- Optional integrations are optional because hard dependencies become pain later.

### 2.2 Core domains
- Armor profiles
- Projectile profiles and impact mutation
- Process recipes/events
- WariumVS bridge surfaces
- Radar/effects integrations
- Vehicle/tools/munitions/aimer optional surfaces

### 2.3 Common anti-patterns
- Doing heavy calculations inside frequently fired events.
- Allocating objects per-tick in hot handlers.
- Assuming optional services exist in every pack.
- Changing API IDs casually and calling it “cleanup”.

---

## 3) Services and events reference

### 3.1 Always available
- `armor()`
- `ballistics()`

### 3.2 Optional services (feature/mod dependent)
- `process()`
- `vs()`
- `radar()`
- `effects()`
- `vehicle()`
- `tools()`
- `munitions()`
- `aimer()`

### 3.3 Key events
- `ProjectileImpactEvent`
- `ProcessStartedEvent`
- `ProcessCompletedEvent`
- `WariumApiReadyEvent`
- `VehicleControlNodeActivatedEvent`
- `VehicleControlInputEvent`
- `WrenchUseEvent`
- `MunitionLaunchedEvent`
- `MunitionDetonateEvent`

### 3.4 Event handling expectations
- Keep handlers deterministic.
- Avoid world mutation off-thread.
- Prefer coarse-grained config toggles over hard-coded behaviour.
- Add telemetry when you suppress or alter gameplay paths.

---

## 4) Registry, key, and compatibility policy

### 4.1 Registry sanity rules
- Keep IDs stable and namespaced.
- Prefer explicit key lookups over loose string checks.
- Document new IDs where users can actually find them.

### 4.2 Compatibility promises
- Additive changes first.
- Breaking changes require migration notes.
- Deprecation should provide replacement APIs before removal.

### 4.3 Versioning mood board
- Semantic versioning, but with accountability.
- If addon authors must rewrite half their code, that’s not a “tiny patch”.

---

## 5) Addon author playbook

### 5.1 Startup and registration
- Register content at deterministic startup points.
- Keep bootstrapping idempotent where possible.
- Fail clearly if required contract data is missing.

### 5.2 Runtime behaviour
- Keep hot paths lean.
- Guard optional services with `ifPresent`.
- Do not assume tick rate is stable under load.

### 5.3 Threading
- Treat game state as main-thread sensitive unless explicitly safe.
- Use async only for non-world computation and return safely.

### 5.4 Release checklist for addon pages
- What it adds
- Why anyone should care
- Compatibility matrix
- Honest performance notes
- Config knobs and defaults
- Install steps
- FAQ
- Changelog

Yes, users still ask questions answered directly in the page. No, we cannot fix that with code.

---

## 6) Performance operations (WariumVS) — the survival chapter

### 6.1 Typical failure profile
- TPS collapse with high MSPT p95/max.
- VS hotspots dominate server thread in bad states.
- `Er.addTerrainUpdates()` may become a major offender.
- Lag can appear “sudden” after trigger thresholds are crossed.

### 6.2 Mandatory capture checklist
Run these during incidents:
1. `/spark profiler --timeout 180 --thread *`
2. `/spark healthreport`
3. `/spark tps`
4. `/spark tickmonitor`
5. Record player count, active ships, largest ship, clipping state.

### 6.3 A/B matrix (repeatable and painfully necessary)
1. Baseline stable fleet
2. Wheels/tracks stress
3. Clipping stress
4. Async/perf mods ON vs OFF
5. Connectivity mode A/B
6. Large-plane stress

Run each scenario for 20–30 minutes in equivalent conditions. Short runs only prove you enjoy uncertainty.

### 6.4 PASS/WARN/FAIL thresholds
- PASS: TPS >= 18 sustained, MSPT p95 < 50 ms, no recurring 1s+ spikes
- WARN: TPS 14–18, MSPT p95 50–100 ms, intermittent major spikes
- FAIL: TPS < 14, MSPT p95 > 100 ms, terrain-update hotspot dominance

### 6.5 Performance guard summary
Config file: `wariumapi-performance.toml`

What it does:
- Degraded-mode load shedding
- Critical-mode detonation suppression
- Input deadzone/min-interval smoothing
- Per-controller backpressure and per-class budgets
- Telemetry counters for tuning

What it does not do:
- Rewrite VS internals
- Fix host-level IO/swap pressure by magic
- Compensate for unlimited clipping chaos forever

### 6.6 Recommended starting posture
- Start with degraded/critical thresholds around 18/14 TPS.
- Keep diagnostics on during tuning.
- Tighten guard settings during incidents, then ease back once stable.
- Re-test after every major modpack change.

### 6.7 Incident flow (copy this to your ops channel)
1. Capture profile immediately.
2. Confirm hotspot signature.
3. Check clipping/new heavy vehicles.
4. Tighten guard.
5. Re-test with async mods disabled.
6. Compare with baseline and classify result.

---

## 7) Engineering snapshot (implemented vs next)

### 7.1 Implemented in API layer
- Config/state plumbing
- Tick monitor and guard mode transitions
- Guard handlers and telemetry

### 7.2 Next sensible work
- Preset bundles (small/medium/large server)
- Optional intensity ladder before hard cancellation
- Extended diagnostics for incident summaries

### 7.3 Practical warning
Tuning is trade-off work. If you remove all limits, lag returns. If you over-limit, gameplay feels dead. Pick your compromise like an adult.

---

## 8) Public comms template (for when players ask “server dead?”)

When posting updates to players/admins:
- Explain symptoms in plain English.
- Separate confirmed findings from likely factors.
- List what changed now vs what’s still in progress.
- Give practical actions server owners can take.
- Avoid “fixed forever” promises unless you adore eating your words later.

Suggested mini-format:
- **Issue observed:**
- **Evidence so far:**
- **Mitigation applied:**
- **Next validation step:**
- **Current server guidance:**

---

## 9) Nuclear and specialised registries note

If your addon touches nuclear/warhead/fuel systems:
- Document exact registry IDs.
- Keep config and balancing changes explicit.
- Test client/server parity and integration side effects.
- Include rollback guidance before public release.

If the feature can explode both gameplay and performance, test it twice and deploy it once.

---

## 10) Troubleshooting table (because guesswork is not a strategy)

### 10.1 Symptom: intermittent stalls despite okay average TPS
Likely causes:
- High MSPT p95 tails
- Chunk-boundary pressure
- Entity-heavy local hotspots

Do this:
- Check tickmonitor and Spark max timings.
- Reduce hotspot density and compare profile deltas.

### 10.2 Symptom: sudden collapse after new vehicle builds
Likely causes:
- Clipping/overlap
- Wheels/tracks pressure
- Large-plane geometry stress

Do this:
- Run A/B with and without problematic vehicles.
- Enforce temporary build constraints.

### 10.3 Symptom: behaviour differs across modpacks
Likely causes:
- Scheduler/async interaction changes
- Different content density and update patterns

Do this:
- Controlled ON/OFF pack deltas.
- Keep one known-good baseline pack for sanity checks.

---

## 11) GitHub release prep checklist

- Version updated (`1.0.5` here).
- Changelog entry added.
- Contract changes documented in this handbook.
- Docs links checked.
- Incident/perf guidance reflects current code.
- No “TODO maybe later” in user-facing release notes.
- Remind myself to actually update the Github...

---

## 12) Final reminder (tattoo this on your CI if needed)

Profile first, tune second, argue in Discord third.

And if someone says “it can’t be clipping, trust me,” profile that first.
