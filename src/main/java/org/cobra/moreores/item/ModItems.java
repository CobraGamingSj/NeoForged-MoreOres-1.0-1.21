package org.cobra.moreores.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.equipment.ArmorType;
import org.cobra.moreores.MoreOresModLoader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.item.equipment.ArmorItem;
import org.cobra.moreores.item.equipment.ModArmorMaterials;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreOresModLoader.MOD_ID);

    public static final DeferredItem<Item> RUBY = registerGem("ruby");
    public static final DeferredItem<Item> RAW_RUBY = registerGem("raw_ruby");
    public static final DeferredItem<Item> RADIANT = registerGem("radiant");
    public static final DeferredItem<Item> RADIANT_DUST = ITEMS.registerItem("radiant_dust", s -> new Item(s.rarity(Rarity.EPIC).fireResistant()));
    public static final DeferredItem<Item> SAPPHIRE = registerGem("sapphire");
    public static final DeferredItem<Item> RAW_SAPPHIRE = ITEMS.registerItem("raw_sapphire", Item::new);
    public static final DeferredItem<Item> GREEN_SAPPHIRE = registerGem("green_sapphire");
    public static final DeferredItem<Item> RAW_GREEN_SAPPHIRE = ITEMS.registerItem("raw_green_sapphire", Item::new);
    public static final DeferredItem<Item> BLUE_GARNET = registerGem("blue_garnet");
    public static final DeferredItem<Item> RAW_BLUE_GARNET = ITEMS.registerItem("raw_blue_garnet", Item::new);
    public static final DeferredItem<Item> PINK_GARNET = registerGem("pink_garnet");
    public static final DeferredItem<Item> RAW_PINK_GARNET = ITEMS.registerItem("raw_pink_garnet", Item::new);
    public static final DeferredItem<Item> GREEN_GARNET = registerGem("green_garnet");
    public static final DeferredItem<Item> RAW_GREEN_GARNET = ITEMS.registerItem("raw_green_garnet", Item::new);
    public static final DeferredItem<Item> KYAWTHUITE = registerGem("kyawthuite");
    public static final DeferredItem<Item> RAW_KYAWTHUITE = ITEMS.registerItem("raw_kyawthuite", Item::new);
    public static final DeferredItem<Item> TOPAZ = registerGem("topaz");
    public static final DeferredItem<Item> RAW_TOPAZ = ITEMS.registerItem("raw_topaz", Item::new);
    public static final DeferredItem<Item> WHITE_TOPAZ = registerGem("white_topaz");
    public static final DeferredItem<Item> RAW_WHITE_TOPAZ = ITEMS.registerItem("raw_white_topaz", Item::new);
    public static final DeferredItem<Item> PERIDOT = registerGem("peridot");
    public static final DeferredItem<Item> RAW_PERIDOT = ITEMS.registerItem("raw_peridot", Item::new);
    public static final DeferredItem<Item> JADE = registerGem("jade");
    public static final DeferredItem<Item> RAW_JADE = ITEMS.registerItem("raw_jade", Item::new);
    public static final DeferredItem<Item> PYROPE = registerGem("pyrope");
    public static final DeferredItem<Item> RAW_PYROPE = ITEMS.registerItem("raw_pyrope", Item::new);

    public static final DeferredItem<Item> CRIMSON_GARNET = registerGem("crimson_garnet");
    public static final DeferredItem<Item> CRYSTALLITE = registerGem("crystallite");
    public static final DeferredItem<Item> RADIANT_AMETHYST = registerGem("radiant_amethyst");
    public static final DeferredItem<Item> MOONSTONE = registerGem("moonstone");
    public static final DeferredItem<Item> LIMESTONE = registerGem("limestone");
    public static final DeferredItem<Item> QUARTSIDIAN = registerGem("quartsidian");
    public static final DeferredItem<Item> ALEXANDRITE = registerGem("alexandrite");
    public static final DeferredItem<Item> ORANGE_ZIRCON = ITEMS.registerItem("orange_zircon", Item::new);
    public static final DeferredItem<Item> OPAL = registerGem("opal");
    public static final DeferredItem<Item> GRANDIDIERITE = ITEMS.registerItem("grandidierite", Item::new);
    public static final DeferredItem<Item> RED_BERYL = registerGem("red_beryl");
    public static final DeferredItem<Item> KASHMIR_SAPPHIRE = ITEMS.registerItem("kashmir_sapphire", Item::new);
    
    public static final DeferredItem<Item> ENERGY_INGOT = ITEMS.registerItem("energy_ingot", EnergyIngotItem::new);

    public static final DeferredItem<Item> RUBY_SWORD = ITEMS.registerItem(
            "ruby_sword", 
            settings -> new Item(settings.fireResistant().sword(ModToolMaterials.RUBY, 6, -2.1f)));
    public static final DeferredItem<Item> RUBY_PICKAXE = ITEMS.registerItem(
            "ruby_pickaxe", 
            settings ->  new Item(settings.fireResistant().pickaxe(ModToolMaterials.RUBY, 2, -3.0f)));
    public static final DeferredItem<Item> RUBY_SHOVEL = ITEMS.registerItem(
            "ruby_shovel", 
            settings ->  new Item(settings.fireResistant().shovel(ModToolMaterials.RUBY, 2.5F, -3.0F)));
    public static final DeferredItem<Item> RUBY_AXE = ITEMS.registerItem(
            "ruby_axe", 
            settings ->  new Item(settings.fireResistant().axe(ModToolMaterials.RUBY, 6.0F, -2.1F)));
    public static final DeferredItem<Item> RUBY_HOE = ITEMS.registerItem(
            "ruby_hoe", 
            settings ->  new Item(settings.fireResistant().hoe(ModToolMaterials.RUBY, -5.0F, 0.0F)));
    public static final DeferredItem<Item> RUBY_SPEAR = ITEMS.registerItem(
            "ruby_spear", 
            settings -> new Item(settings.fireResistant().spear(ModToolMaterials.RUBY,  1.2F,
            1.3F, 0.35F,
            2.0F, 6.5F, 5.0F,
            5.1F, 8.0F, 4.6F)));
    
    public static final DeferredItem<Item> SAPPHIRE_SWORD = ITEMS.registerItem(
            "sapphire_sword",
            s -> new Item(s.fireResistant().sword(ModToolMaterials.SAPPHIRE, 8, -2.0f)));
    public static final DeferredItem<Item> SAPPHIRE_PICKAXE = ITEMS.registerItem(
            "sapphire_pickaxe",
            s -> new Item(s.fireResistant().pickaxe(ModToolMaterials.SAPPHIRE, 4, -3.0f)));
    public static final DeferredItem<Item> SAPPHIRE_AXE = ITEMS.registerItem(
            "sapphire_axe",
            s -> new Item(s.fireResistant().axe(ModToolMaterials.SAPPHIRE, 8, -2.0f)));
    public static final DeferredItem<Item> SAPPHIRE_HOE = ITEMS.registerItem(
            "sapphire_hoe",
            s -> new Item(s.fireResistant().hoe(ModToolMaterials.SAPPHIRE, 4, -3.0f)));
    public static final DeferredItem<Item> SAPPHIRE_SHOVEL = ITEMS.registerItem(
            "sapphire_shovel",
            s -> new Item(s.fireResistant().shovel(ModToolMaterials.SAPPHIRE, 3.5F, -3.0F)));
    public static final DeferredItem<Item> SAPPHIRE_SPEAR = ITEMS.registerItem(
            "sapphire_spear", 
            settings -> new Item(settings.fireResistant().spear(ModToolMaterials.SAPPHIRE, 1.25F, 1.4F,
            0.3F, 1.5F, 6.0F, 4.5F,
            5.1F, 7.65F, 4.6F)));

    public static final DeferredItem<Item> RADIANT_SWORD = ITEMS.registerItem(
            "radiant_sword",
            s -> new Item(s.rarity(Rarity.EPIC).fireResistant().sword(
                    ModToolMaterials.RADIANT, 32, -1f
            ))
    );
    public static final DeferredItem<Item> RADIANT_PICKAXE = ITEMS.registerItem(
            "radiant_pickaxe",
            s -> new Item(s.rarity(Rarity.EPIC).fireResistant().pickaxe(
                    ModToolMaterials.RADIANT, 20, -1.5f
            ))
    );
    public static final DeferredItem<Item> RADIANT_AXE = ITEMS.registerItem(
            "radiant_axe",
            s -> new Item(s.rarity(Rarity.EPIC).fireResistant().axe(
                    ModToolMaterials.RADIANT, 32, -1f
            ))
    );
    public static final DeferredItem<Item> RADIANT_SHOVEL = ITEMS.registerItem(
            "radiant_shovel",
            s -> new Item(s.rarity(Rarity.EPIC).fireResistant().shovel(
                    ModToolMaterials.RADIANT, 16, -1.8f
            ))
    );
    public static final DeferredItem<Item> RADIANT_HOE = ITEMS.registerItem(
            "radiant_hoe",
            s -> new Item(s.rarity(Rarity.EPIC).fireResistant().hoe(
                    ModToolMaterials.RADIANT, 12, -2.2f
            ))
    );
    
    public static final DeferredItem<Item> RUBY_HELMET = ITEMS.registerItem(
            "ruby_helmet",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.RUBY, ArmorType.HELMET).fireResistant())
    );
    public static final DeferredItem<Item> RUBY_CHESTPLATE = ITEMS.registerItem(
            "ruby_chestplate",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.RUBY, ArmorType.CHESTPLATE).fireResistant())
    );
    public static final DeferredItem<Item> RUBY_LEGGINGS = ITEMS.registerItem(
            "ruby_leggings",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.RUBY, ArmorType.LEGGINGS).fireResistant())
    );
    public static final DeferredItem<Item> RUBY_BOOTS = ITEMS.registerItem(
            "ruby_boots",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.RUBY, ArmorType.BOOTS).fireResistant())
    );
    public static final DeferredItem<Item> RUBY_NAUTILUS_ARMOR = ITEMS.registerItem(
            "ruby_nautilus_armor",
            s -> new Item(s.nautilusArmor(ModArmorMaterials.RUBY).fireResistant())
    );
    
    public static final DeferredItem<Item> SAPPHIRE_HELMET = ITEMS.registerItem(
            "sapphire_helmet",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.SAPPHIRE, ArmorType.HELMET).fireResistant())
    );
    public static final DeferredItem<Item> SAPPHIRE_CHESTPLATE = ITEMS.registerItem(
            "sapphire_chestplate",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.SAPPHIRE, ArmorType.CHESTPLATE).fireResistant())
    );
    public static final DeferredItem<Item> SAPPHIRE_LEGGINGS = ITEMS.registerItem(
            "sapphire_leggings",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.SAPPHIRE, ArmorType.LEGGINGS).fireResistant())
    );
    public static final DeferredItem<Item> SAPPHIRE_BOOTS = ITEMS.registerItem(
            "sapphire_boots",
            s -> new Item(s.humanoidArmor(ModArmorMaterials.SAPPHIRE, ArmorType.BOOTS).fireResistant())
    );
    public static final DeferredItem<Item> SAPPHIRE_NAUTILUS_ARMOR = ITEMS.registerItem(
            "sapphire_nautilus_armor",
            s -> new Item(s.nautilusArmor(ModArmorMaterials.SAPPHIRE).fireResistant())
    );
    
    public static final DeferredItem<Item> RADIANT_HELMET = ITEMS.registerItem(
            "radiant_helmet",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).humanoidArmor(ModArmorMaterials.RADIANT, ArmorType.HELMET).fireResistant())
    );
    public static final DeferredItem<Item> RADIANT_CHESTPLATE = ITEMS.registerItem(
            "radiant_chestplate",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).humanoidArmor(ModArmorMaterials.RADIANT, ArmorType.CHESTPLATE).fireResistant())
    );
    public static final DeferredItem<Item> RADIANT_LEGGINGS = ITEMS.registerItem(
            "radiant_leggings",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).humanoidArmor(ModArmorMaterials.RADIANT, ArmorType.LEGGINGS).fireResistant())
    );
    public static final DeferredItem<Item> RADIANT_BOOTS = ITEMS.registerItem(
            "radiant_boots",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).humanoidArmor(ModArmorMaterials.RADIANT, ArmorType.BOOTS).fireResistant())
    );

    public static final DeferredItem<Item> RUBY_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("ruby_upgrade_smithing_template",
            s -> ModSmithingTemplateItem.createRubyUpgrade(s.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> RADIANT_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("radiant_upgrade_smithing_template",
            s -> ModSmithingTemplateItem.createRadiantUpgrade(s.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.registerItem("guardian_armor_trim_smithing_template",
            s -> SmithingTemplateItem.createArmorTrimTemplate(s.rarity(Rarity.RARE)));
    
    public static final DeferredItem<Item> CRYSTAL_OF_ECLIPSE = ITEMS.registerItem("crystal_of_eclipse", settings -> new Item(settings.rarity(Rarity.RARE).fireResistant()));
    public static final DeferredItem<Item> ECLIPSE_GEM = ITEMS.registerItem("eclipse_gem", settings -> new Item(settings.rarity(Rarity.EPIC).fireResistant()));
    
    private static DeferredItem<Item> registerGem(String name) {
        return ITEMS.registerItem(name, GemItem::new);
    }
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreOresModLoader.LOGGER.info("Loading ModItems for " + MoreOresModLoader.MOD_ID + " mod.");
    }
}