package org.cobra.moreores.item;

import net.minecraft.world.item.Item;
import org.cobra.moreores.MoreOresModLoader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreOresModLoader.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.registerItem("ruby", GemItem::new);
    public static final DeferredItem<Item> RAW_RUBY = ITEMS.registerItem("raw_ruby", GemItem::new);
    public static final DeferredItem<Item> RADIANT = ITEMS.registerItem("radiant", GemItem::new);
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.registerItem("sapphire", GemItem::new);
    public static final DeferredItem<Item> GREEN_SAPPHIRE = ITEMS.registerItem("green_sapphire", GemItem::new);
    public static final DeferredItem<Item> BLUE_GARNET = ITEMS.registerItem("blue_garnet", GemItem::new);
    public static final DeferredItem<Item> PINK_GARNET = ITEMS.registerItem("pink_garnet", GemItem::new);
    public static final DeferredItem<Item> GREEN_GARNET = ITEMS.registerItem("green_garnet", GemItem::new);
    public static final DeferredItem<Item> KYAWTHUITE = ITEMS.registerItem("kyawthuite", GemItem::new);
    public static final DeferredItem<Item> TOPAZ = ITEMS.registerItem("topaz", GemItem::new);
    public static final DeferredItem<Item> WHITE_TOPAZ = ITEMS.registerItem("white_topaz", GemItem::new);
    public static final DeferredItem<Item> PERIDOT = ITEMS.registerItem("peridot", GemItem::new);
    public static final DeferredItem<Item> JADE = ITEMS.registerItem("jade", GemItem::new);
    public static final DeferredItem<Item> PYROPE = ITEMS.registerItem("pyrope", GemItem::new);
    public static final DeferredItem<Item> ENERGY_INGOT = ITEMS.registerItem("energy_ingot", EnergyIngotItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreOresModLoader.LOGGER.info("Loading ModItems for " + MoreOresModLoader.MOD_ID + " mod.");
    }

}
