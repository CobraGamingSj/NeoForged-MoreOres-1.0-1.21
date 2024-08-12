package net.moreores.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.moreores.MoreOres;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreOres.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> RADIANT = ITEMS.register("radiant", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> GREEN_SAPPHIRE = ITEMS.register("green_sapphire", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> BLUE_GARNET = ITEMS.register("blue_garnet", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> PINK_GARNET = ITEMS.register("pink_garnet", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> GREEN_GARNET = ITEMS.register("green_garnet", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> TOPAZ = ITEMS.register("topaz", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> WHITE_TOPAZ = ITEMS.register("white_topaz", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> PERIDOT = ITEMS.register("peridot", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> JADE = ITEMS.register("jade", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> PYROPE = ITEMS.register("pyrope", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreOres.LOGGER.info("Loading ModItems for " + MoreOres.MOD_ID + " mod.");
    }

}
