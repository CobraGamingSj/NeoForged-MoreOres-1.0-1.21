package org.cobra.moreores.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.cobra.moreores.MoreOresModLoader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreOresModLoader.MOD_ID);

    public static final Supplier<CreativeModeTab> GEMSTONES = CREATIVE_MODE_TAB.register("gemstones",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBY.get()))
                    .title(Component.translatable("creativeModeTab.moreores.gemstones"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RUBY);
                        output.accept(ModItems.RADIANT);
                        output.accept(ModItems.SAPPHIRE);
                        output.accept(ModItems.GREEN_SAPPHIRE);
                        output.accept(ModItems.BLUE_GARNET);
                        output.accept(ModItems.PINK_GARNET);
                        output.accept(ModItems.GREEN_GARNET);
                        output.accept(ModItems.KYAWTHUITE);
                        output.accept(ModItems.TOPAZ);
                        output.accept(ModItems.WHITE_TOPAZ);
                        output.accept(ModItems.PERIDOT);
                        output.accept(ModItems.JADE);
                        output.accept(ModItems.PYROPE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
