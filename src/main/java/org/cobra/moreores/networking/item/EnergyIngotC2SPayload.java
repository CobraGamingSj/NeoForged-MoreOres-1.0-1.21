package org.cobra.moreores.networking.item;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import org.cobra.moreores.MoreOresModLoader;

public record EnergyIngotC2SPayload() implements CustomPacketPayload {
    public static final Type<EnergyIngotC2SPayload> TYPE = new Type<>(MoreOresModLoader.id("energy_ingot_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnergyIngotC2SPayload> PACKET_CODEC = StreamCodec.ofMember(
            (value, buf) -> {}, buf -> new EnergyIngotC2SPayload()
    );

    public static void handle(EnergyIngotC2SPayload payload, ServerPlayer user) {
        Level world = user.level();
        EntityType<LightningBolt> lightningType = EntityType.LIGHTNING_BOLT;
        LightningBolt lightning = new LightningBolt(lightningType, world);
        lightning.setPosRaw(user.getX(), user.getY(), user.getZ());
        world.addFreshEntity(lightning);
        user.addEffect(new MobEffectInstance(MobEffects.POISON, 60));
        user.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 60));
        user.removeAllEffects();
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 2.0f, 1.0f);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
