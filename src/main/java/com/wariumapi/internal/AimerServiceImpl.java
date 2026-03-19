package com.wariumapi.internal;

import com.wariumapi.aimer.AimerService;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

final class AimerServiceImpl implements AimerService {
    private static final String DATA_NAME = "wariumapi_aimer_links";

    @Override
    public boolean linkNode(Level level, BlockPos launcherPos, BlockPos targetPos) {
        if (level == null || launcherPos == null || targetPos == null || level.isClientSide()) {
            return false;
        }
        AimerSavedData data = getData(level);
        if (data == null) {
            return false;
        }
        return data.put(level.dimension().location().toString(), launcherPos, targetPos);
    }

    @Override
    public boolean clearNode(Level level, BlockPos launcherPos) {
        if (level == null || launcherPos == null || level.isClientSide()) {
            return false;
        }
        AimerSavedData data = getData(level);
        if (data == null) {
            return false;
        }
        return data.remove(level.dimension().location().toString(), launcherPos);
    }

    @Override
    public Optional<BlockPos> getLinkedTarget(Level level, BlockPos launcherPos) {
        if (level == null || launcherPos == null || level.isClientSide()) {
            return Optional.empty();
        }
        AimerSavedData data = getData(level);
        if (data == null) {
            return Optional.empty();
        }
        return data.get(level.dimension().location().toString(), launcherPos);
    }

    private static AimerSavedData getData(Level level) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        MinecraftServer server = serverLevel.getServer();
        if (server == null || server.overworld() == null) {
            return null;
        }
        return server.overworld().getDataStorage().computeIfAbsent(AimerSavedData::load, AimerSavedData::new, DATA_NAME);
    }

    private static final class LauncherKey {
        private final String dimensionId;
        private final long launcherPos;

        private LauncherKey(String dimensionId, long launcherPos) {
            this.dimensionId = dimensionId;
            this.launcherPos = launcherPos;
        }

        private static LauncherKey of(String dimensionId, BlockPos launcherPos) {
            return new LauncherKey(dimensionId, launcherPos.asLong());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LauncherKey other)) {
                return false;
            }
            return launcherPos == other.launcherPos && dimensionId.equals(other.dimensionId);
        }

        @Override
        public int hashCode() {
            return 31 * dimensionId.hashCode() + Long.hashCode(launcherPos);
        }
    }

    private static final class AimerSavedData extends SavedData {
        private final Map<LauncherKey, BlockPos> links = new ConcurrentHashMap<>();

        static AimerSavedData load(CompoundTag tag) {
            AimerSavedData data = new AimerSavedData();
            data.read(tag);
            return data;
        }

        synchronized boolean put(String dimensionId, BlockPos launcherPos, BlockPos targetPos) {
            LauncherKey key = LauncherKey.of(dimensionId, launcherPos);
            BlockPos previous = links.put(key, targetPos.immutable());
            if (!targetPos.equals(previous)) {
                setDirty();
            }
            return true;
        }

        synchronized boolean remove(String dimensionId, BlockPos launcherPos) {
            LauncherKey key = LauncherKey.of(dimensionId, launcherPos);
            BlockPos removed = links.remove(key);
            if (removed != null) {
                setDirty();
                return true;
            }
            return false;
        }

        synchronized Optional<BlockPos> get(String dimensionId, BlockPos launcherPos) {
            LauncherKey key = LauncherKey.of(dimensionId, launcherPos);
            BlockPos target = links.get(key);
            return target == null ? Optional.empty() : Optional.of(target.immutable());
        }

        synchronized void read(CompoundTag tag) {
            links.clear();
            ListTag list = tag.getList("links", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag entry = list.getCompound(i);
                String dimensionId = entry.getString("dimension");
                BlockPos launcher = BlockPos.of(entry.getLong("launcher"));
                BlockPos target = BlockPos.of(entry.getLong("target"));
                links.put(LauncherKey.of(dimensionId, launcher), target.immutable());
            }
        }

        @Override
        public synchronized CompoundTag save(CompoundTag tag) {
            ListTag list = new ListTag();
            for (Map.Entry<LauncherKey, BlockPos> entry : links.entrySet()) {
                CompoundTag linkTag = new CompoundTag();
                linkTag.putString("dimension", entry.getKey().dimensionId);
                linkTag.putLong("launcher", entry.getKey().launcherPos);
                linkTag.putLong("target", entry.getValue().asLong());
                list.add(linkTag);
            }
            tag.put("links", list);
            return tag;
        }
    }
}
