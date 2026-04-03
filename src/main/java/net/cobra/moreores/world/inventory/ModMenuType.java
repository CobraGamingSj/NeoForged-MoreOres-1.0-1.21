package net.cobra.moreores.world.inventory;

import net.cobra.moreores.MoreOresModLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuType {
    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(Registries.MENU, MoreOresModLoader.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GemPolisherMenu>> GEM_POLISHER_MENU = register("gem_polisher", GemPolisherMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> register(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENU_TYPE.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPE.register(eventBus);
    }

}
