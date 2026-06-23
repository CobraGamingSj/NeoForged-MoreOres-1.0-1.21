package org.cobra.moreores.item.equipment;

import com.google.common.collect.ImmutableMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.Equippable;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ArmorItem extends Item {

    private boolean isFalling = false;

    private static final Map<ArmorMaterial, List<MobEffectInstance>> ARMOR_EFFECTS = new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>()
            .put(ModArmorMaterials.RADIANT, List.of(
                    new MobEffectInstance(MobEffects.REGENERATION, -1, 3, false, false, false),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, -1, 9, false, false, false)
            )).build();

    public ArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        if(level.isClientSide()) {
            return;
        }

        if(entity instanceof Player player) {
            if(hasFullSuitOfArmorOn(player)) {
                if(player.fallDistance >= 5) {
                    if(!isFalling) {
                        int duration = (int) player.fallDistance;
                        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, 2, false, false, false));
                        if (slot == EquipmentSlot.FEET) {
                            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                            boots.hurtAndBreak((int) player.fallDistance / 3, player, slot);
                        }
                    }
                    isFalling = true;
                } else {
                    if(isFalling) {
                        player.removeEffect(MobEffects.SLOW_FALLING);
                        isFalling = false;
                    }
                }
                evaluateArmorEffects(player);
            } else {
                player.removeEffect(MobEffects.REGENERATION);
                player.removeEffect(MobEffects.HEALTH_BOOST);
                player.removeEffect(MobEffects.SLOW_FALLING);
                isFalling = false;
            }
        }
        super.inventoryTick(stack, level, entity, slot);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : ARMOR_EFFECTS.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapMobEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapMobEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, List<MobEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(MobEffectInstance -> player.hasEffect(MobEffectInstance.getEffect()));

        if(!hasPlayerEffect) {
            for (MobEffectInstance instance : mapStatusEffect) {
                player.addEffect(new MobEffectInstance(instance.getEffect(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.isVisible()));
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        Equippable equippableComponentBoots = boots.getComponents().get(DataComponents.EQUIPPABLE);
        Equippable equippableComponentLeggings = leggings.getComponents().get(DataComponents.EQUIPPABLE);
        Equippable equippableComponentBreastplate = chestplate.getComponents().get(DataComponents.EQUIPPABLE);
        Equippable equippableComponentHelmet = helmet.getComponents().get(DataComponents.EQUIPPABLE);

        if(equippableComponentHelmet == null || equippableComponentBreastplate == null || equippableComponentLeggings == null || equippableComponentBoots == null) {
            return false;
        }
        
        return equippableComponentBoots.assetId().get().equals(material.assetId()) &&
                equippableComponentLeggings.assetId().get().equals(material.assetId()) &&
                equippableComponentBreastplate.assetId().get().equals(material.assetId()) &&
                equippableComponentHelmet.assetId().get().equals(material.assetId());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, display, builder, tooltipFlag);
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        if (player != null && hasFullSuitOfArmorOn(player)) {
            builder.accept(Component.literal("Applied Effects: ")
                    .withStyle(ChatFormatting.YELLOW));
            List<MobEffectInstance> effects = ARMOR_EFFECTS.get(ModArmorMaterials.RADIANT);
            if(effects != null) {
                for (MobEffectInstance effect : effects) {
                    builder.accept(Component.translatable(effect.getDescriptionId()).append(" " + (effect.getAmplifier() + 1)).withStyle(ChatFormatting.GRAY));
                }
            }
            Equippable self = stack.getComponents().get(DataComponents.EQUIPPABLE);
            if (self != null && self.assetId().isPresent()
                    && self.assetId().get().equals(ModArmorMaterials.RADIANT.assetId())
                    && self.slot() == EquipmentSlot.FEET) {
                builder.accept(Component.literal("Fall Protection Activated").withStyle(ChatFormatting.BLUE));
            }
        }
    }
}
