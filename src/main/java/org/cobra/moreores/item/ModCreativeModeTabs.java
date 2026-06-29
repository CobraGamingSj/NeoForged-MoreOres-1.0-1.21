package org.cobra.moreores.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.cobra.moreores.MoreOresModLoader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.block.ModBlocks;

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
                        output.accept(ModItems.CRIMSON_GARNET);
                        output.accept(ModItems.CRYSTALLITE);
                        output.accept(ModItems.RADIANT_AMETHYST);
                        output.accept(ModItems.MOONSTONE);
                        output.accept(ModItems.LIMESTONE);
                        output.accept(ModItems.QUARTSIDIAN);
                        output.accept(ModItems.ALEXANDRITE);
                        output.accept(ModItems.ORANGE_ZIRCON);
                        output.accept(ModItems.OPAL);
                        output.accept(ModItems.GRANDIDIERITE);
                        output.accept(ModItems.RED_BERYL);
                        output.accept(ModItems.KASHMIR_SAPPHIRE);
                        output.accept(ModBlocks.RUBY_BLOCK);
                        output.accept(ModBlocks.RADIANT_BLOCK);
                        output.accept(ModBlocks.SAPPHIRE_BLOCK);
                        output.accept(ModBlocks.GREEN_SAPPHIRE_BLOCK);
                        output.accept(ModBlocks.BLUE_GARNET_BLOCK);
                        output.accept(ModBlocks.PINK_GARNET_BLOCK);
                        output.accept(ModBlocks.GREEN_GARNET_BLOCK);
                        output.accept(ModBlocks.KYAWTHUITE_BLOCK);
                        output.accept(ModBlocks.TOPAZ_BLOCK);
                        output.accept(ModBlocks.WHITE_TOPAZ_BLOCK);
                        output.accept(ModBlocks.PERIDOT_BLOCK);
                        output.accept(ModBlocks.JADE_BLOCK);
                        output.accept(ModBlocks.PYROPE_BLOCK);
                        output.accept(ModBlocks.CRIMSON_GARNET_BLOCK);
                        output.accept(ModBlocks.CRYSTALLITE_BLOCK);
                        output.accept(ModBlocks.RADIANT_AMETHYST_BLOCK);
                        output.accept(ModBlocks.MOONSTONE_BLOCK);
                        output.accept(ModBlocks.LIMESTONE_BLOCK);
                        output.accept(ModBlocks.QUARTSIDIAN_BLOCK);
                        output.accept(ModBlocks.ALEXANDRITE_BLOCK);
                        output.accept(ModBlocks.ORANGE_ZIRCON_BLOCK);
                        output.accept(ModBlocks.OPAL_BLOCK);
                        output.accept(ModBlocks.GRANDIDIERITE_BLOCK);
                        output.accept(ModBlocks.RED_BERYL_BLOCK);
                        output.accept(ModBlocks.KASHMIR_SAPPHIRE_BLOCK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
