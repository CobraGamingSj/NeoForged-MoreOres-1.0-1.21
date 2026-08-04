package org.cobra.moreores.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.enchantment.entity.effect.ThunderStrikerEnchantmentEffect;

public class ModEnchantments {

    public static final ResourceKey<Enchantment> THUNDER_STRIKER = of("thunder_striker");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, THUNDER_STRIKER, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.SWORDS),
                        5,
                        4,
                        Enchantment.dynamicCost(7, 10),
                        Enchantment.dynamicCost(25, 9),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new ThunderStrikerEnchantmentEffect()));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }

    private static ResourceKey<Enchantment> of(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, MoreOresModLoader.id(id));
    }
}
