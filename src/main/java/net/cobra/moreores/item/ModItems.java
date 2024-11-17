package net.cobra.moreores.item;

import net.cobra.moreores.item.equipment.ModArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.block.jukebox.ModJukeboxSongs;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreOresModLoader.MOD_ID);

    public static final DeferredItem<Item> RUBY = register("ruby",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby")))));
    public static final DeferredItem<Item> RADIANT = register("radiant",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "radiant")))));
    public static final DeferredItem<Item> SAPPHIRE = register("sapphire",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "sapphire")))));
    public static final DeferredItem<Item> GREEN_SAPPHIRE = register("green_sapphire",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "green_sapphire")))));
    public static final DeferredItem<Item> BLUE_GARNET = register("blue_garnet",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "blue_garnet")))));
    public static final DeferredItem<Item> PINK_GARNET = register("pink_garnet",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "pink_garnet")))));
    public static final DeferredItem<Item> GREEN_GARNET = register("green_garnet",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "green_garnet")))));
    public static final DeferredItem<Item> TOPAZ = register("topaz",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "topaz")))));
    public static final DeferredItem<Item> WHITE_TOPAZ = register("white_topaz",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "white_topaz")))));
    public static final DeferredItem<Item> PERIDOT = register("peridot",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "peridot")))));
    public static final DeferredItem<Item> JADE = register("jade",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "jade")))));
    public static final DeferredItem<Item> PYROPE = register("pyrope",  new Item(new Item.Properties().rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "pyrope")))));
    public static final DeferredItem<Item> ENERGY_INGOT = register("energy_ingot",  new EnergyIngotItem(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "energy_ingot")))));

    public static final DeferredItem<Item> RUBY_HELMET = register(
            "ruby_helmet",
                    new ArmorItem(
                            ModArmorMaterials.RUBY, ArmorType.HELMET, new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby_helmet"))))
    );
    public static final DeferredItem<Item> RUBY_CHESTPLATE = register(
            "ruby_chestplate",
                    new ArmorItem(
                            ModArmorMaterials.RUBY, ArmorType.CHESTPLATE, new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby_chestplate"))))
    );
    public static final DeferredItem<Item> RUBY_LEGGINGS = register(
            "ruby_leggings",
                    new ArmorItem(
                            ModArmorMaterials.RUBY, ArmorType.LEGGINGS, new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby_leggings"))))
    );
    public static final DeferredItem<Item> RUBY_BOOTS = register(
            "ruby_boots",
                    new ArmorItem(ModArmorMaterials.RUBY, ArmorType.BOOTS, new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby_boots"))))
    );

    public static final DeferredItem<Item> MUSIC_DISC_TASWELL = register("music_disc_taswell",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.TASWELL).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_taswell")))));
    public static final DeferredItem<Item> MUSIC_DISC_DREITON = register("music_disc_dreiton",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.DREITON).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_dreiton")))));
    public static final DeferredItem<Item> MUSIC_DISC_BIOME_FEST = register("music_disc_biome_fest",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.BIOME_FEST).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_biome_fest")))));
    public static final DeferredItem<Item> MUSIC_DISC_ARIA_MATH = register("music_disc_aria_math",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.ARIA_MATH).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_aria_math")))));
    public static final DeferredItem<Item> MUSIC_DISC_INFINITE_AMETHYST = register("music_disc_infinite_amethyst",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.INFINITE_AMETHYST).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_infinite_amethyst")))));
    public static final DeferredItem<Item> MUSIC_DISC_FEATHERFALL = register("music_disc_featherfall",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.FEATHERFALL).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_featherfall")))));
    public static final DeferredItem<Item> MUSIC_DISC_ENDLESS = register("music_disc_endless", new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.ENDLESS).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_endless")))));
    public static final DeferredItem<Item> MUSIC_DISC_DEEPER = register("music_disc_deeper",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.DEEPER).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_deeper")))));
    public static final DeferredItem<Item> MUSIC_DISC_WATCHER = register("music_disc_watcher",  new Item(new Item.Properties().jukeboxPlayable(ModJukeboxSongs.WATCHER).rarity(Rarity.RARE)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "music_disc_watcher")))));

    public static final DeferredItem<Item> GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE = register("guardian_armor_trim_smithing_template",  SmithingTemplateItem.createArmorTrimTemplate(new Item.Properties().rarity(Rarity.EPIC)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "guardian_armor_trim_smithing_template")))));

    private static DeferredItem<Item> register(String id, Item item) {
        return ITEMS.register(id, () -> item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreOresModLoader.LOGGER.info("Loading ModItems for " + MoreOresModLoader.MOD_ID + " mod.");
    }
}
