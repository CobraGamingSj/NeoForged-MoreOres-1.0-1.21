package org.cobra.moreores.enchantment.entity.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOresModLoader;

import java.util.function.Supplier;

import static org.cobra.moreores.MoreOresModLoader.LOGGER;

public class EnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MoreOresModLoader.MOD_ID);
    
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> THUNDER_STRIKER = register("thunder_striker", () -> ThunderStrikerEnchantmentEffect.CODEC);
    
    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>> register(String id, Supplier<MapCodec<? extends EnchantmentEntityEffect>> codec) {
        return ENCHANTMENTS.register(id, codec);
    }

    public static void register(IEventBus schoolBus) {
        ENCHANTMENTS.register(schoolBus);
        LOGGER.info("Loading EnchantmentEffects for " + MoreOresModLoader.MOD_ID + " mod.");
    }
}
