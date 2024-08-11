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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreOres.LOGGER.info("Loading ModItems for " + MoreOres.MOD_ID + " mod.");
    }

}
