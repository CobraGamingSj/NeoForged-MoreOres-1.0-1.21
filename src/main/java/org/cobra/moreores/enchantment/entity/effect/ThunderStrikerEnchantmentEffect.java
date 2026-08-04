package org.cobra.moreores.enchantment.entity.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ThunderStrikerEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<ThunderStrikerEnchantmentEffect> CODEC = MapCodec.unit(ThunderStrikerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        if (level == 1) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
        }
        if (level == 2) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
        }
        if (level == 3) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.TNT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED).setFuse(0);
        }
        if(level == 4) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY(), user.getBlockZ()), EntitySpawnReason.TRIGGERED).setFuse(0);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX() + 2, user.getBlockY(), user.getBlockZ()), EntitySpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX() - 2, user.getBlockY(), user.getBlockZ()), EntitySpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY() + 2, user.getBlockZ()), EntitySpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY(), user.getBlockZ() + 2), EntitySpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY(), user.getBlockZ() - 2), EntitySpawnReason.TRIGGERED).setFuse(10);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
