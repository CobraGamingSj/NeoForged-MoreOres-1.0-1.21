package org.cobra.moreores.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.cobra.moreores.MoreOres;
import org.cobra.moreores.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreOres.MOD_ID);

    public static final DeferredBlock<Block> ENERGY_BLOCK = registerBlock("energy_block",
            p -> new EnergyBlock(p
                    .strength(256f).strength(512f, 256f)));
    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RADIANT_BLOCK = registerBlock("radiant_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GREEN_SAPPHIRE_BLOCK = registerBlock("green_sapphire_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLUE_GARNET_BLOCK = registerBlock("blue_garnet_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_BLUE).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_PINK).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> GREEN_GARNET_BLOCK = registerBlock("green_garnet_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    
    public static final DeferredBlock<Block> GEM_PURIFIER_BLOCK = registerBlock("gem_purifier_block",
            p -> new GemPurifierBlock(p.requiresCorrectToolForDrops().noOcclusion()));
    
    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore",
            p -> new DropExperienceBlock(UniformInt.of(3, 5), p
                    .strength(5.5f).strength(5.5f, 5.5f).mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            p -> new DropExperienceBlock(UniformInt.of(2, 4), p
                    .strength(6f).strength(6f, 6f).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        MoreOres.LOGGER.info("Loading ModBlocks for" + MoreOres.MOD_ID + " mod.");
    }
}