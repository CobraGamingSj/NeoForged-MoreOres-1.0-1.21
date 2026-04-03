package net.cobra.moreores.item;

import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.block.jukebox.ModJukeboxSongs;
import net.cobra.moreores.item.equipment.ModArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreOresModLoader.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.registerItem("ruby", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> RADIANT = ITEMS.registerItem("radiant", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.registerItem("sapphire", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> GREEN_SAPPHIRE = ITEMS.registerItem("green_sapphire", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> BLUE_GARNET = ITEMS.registerItem("blue_garnet", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> PINK_GARNET = ITEMS.registerItem("pink_garnet", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> GREEN_GARNET = ITEMS.registerItem("green_garnet", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> TOPAZ = ITEMS.registerItem("topaz", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> WHITE_TOPAZ = ITEMS.registerItem("white_topaz", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> PERIDOT = ITEMS.registerItem("peridot", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> JADE = ITEMS.registerItem("jade", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> PYROPE = ITEMS.registerItem("pyrope", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final DeferredItem<Item> ENERGY_INGOT = ITEMS.registerItem("energy_ingot", EnergyIngotItem::new, new Item.Properties().rarity(Rarity.RARE));

    public static final DeferredItem<ArmorItem> RUBY_HELMET = ITEMS.registerItem(
            "ruby_helmet", (properties) ->
                    new ArmorItem(
                            ModArmorMaterials.RUBY, ArmorType.HELMET, properties.fireResistant()));
    public static final DeferredItem<ArmorItem> RUBY_CHESTPLATE = ITEMS.registerItem(
            "ruby_chestplate", (properties) ->
                    new ArmorItem(
                            ModArmorMaterials.RUBY, ArmorType.CHESTPLATE, properties.fireResistant())
    );
    public static final DeferredItem<ArmorItem> RUBY_LEGGINGS = ITEMS.registerItem(
            "ruby_leggings", (properties) ->
                    new ArmorItem(
                            ModArmorMaterials.RUBY, ArmorType.LEGGINGS, properties.fireResistant())
    );
    public static final DeferredItem<ArmorItem> RUBY_BOOTS = ITEMS.registerItem(
            "ruby_boots", (properties) ->
                    new ArmorItem(ModArmorMaterials.RUBY, ArmorType.BOOTS, properties.fireResistant())
    );

    public static final DeferredItem<Item> MUSIC_DISC_TASWELL = ITEMS.registerItem("music_disc_taswell", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.TASWELL).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_DREITON = ITEMS.registerItem("music_disc_dreiton", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.DREITON).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_BIOME_FEST = ITEMS.registerItem("music_disc_biome_fest", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.BIOME_FEST).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_ARIA_MATH = ITEMS.registerItem("music_disc_aria_math", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.ARIA_MATH).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_INFINITE_AMETHYST = ITEMS.registerItem("music_disc_infinite_amethyst", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.INFINITE_AMETHYST).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_FEATHERFALL = ITEMS.registerItem("music_disc_featherfall", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.FEATHERFALL).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_ENDLESS = ITEMS.registerItem("music_disc_endless", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.ENDLESS).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_DEEPER = ITEMS.registerItem("music_disc_deeper", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.DEEPER).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_WATCHER = ITEMS.registerItem("music_disc_watcher", properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.WATCHER).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.registerItem("guardian_armor_trim_smithing_template", properties -> SmithingTemplateItem.createArmorTrimTemplate(properties.rarity(Rarity.EPIC)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreOresModLoader.LOGGER.info("Loading ModItems for " + MoreOresModLoader.MOD_ID + " mod.");
    }
}
