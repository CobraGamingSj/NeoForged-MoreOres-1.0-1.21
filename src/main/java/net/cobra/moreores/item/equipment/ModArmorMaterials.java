package net.cobra.moreores.item.equipment;

import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.tags.ModTags;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static final ArmorMaterial RUBY = new ArmorMaterial(567,
            Util.make(new EnumMap<>(ArmorType.class), (attribute) -> {
                attribute.put(ArmorType.BOOTS, 5);
                attribute.put(ArmorType.LEGGINGS, 8);
                attribute.put(ArmorType.CHESTPLATE, 10);
                attribute.put(ArmorType.HELMET, 5);
                attribute.put(ArmorType.BODY, 13);
            }), 16, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0f, 0.2f, ModTags.Items.REPAIRS_RUBY_ARMOR, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby"));
    public static final ArmorMaterial SAPPHIRE = new ArmorMaterial(641,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 7);
                attribute.put(ArmorType.LEGGINGS, 10);
                attribute.put(ArmorType.CHESTPLATE, 12);
                attribute.put(ArmorType.HELMET, 7);
                attribute.put(ArmorType.BODY, 15);
            }), 17, SoundEvents.ARMOR_EQUIP_DIAMOND, 5.0f, 0.3f, ModTags.Items.REPAIRS_SAPPHIRE_ARMOR, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "sapphire"));

}
