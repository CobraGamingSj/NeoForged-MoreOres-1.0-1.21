package org.cobra.moreores.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreOresModLoader.MOD_ID);

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

    public static final DeferredBlock<Block> KYAWTHUITE_BLOCK = registerBlock("kyawthuite_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TOPAZ_BLOCK = registerBlock("topaz_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> WHITE_TOPAZ_BLOCK = registerBlock("white_topaz_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PERIDOT_BLOCK = registerBlock("peridot_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> JADE_BLOCK = registerBlock("jade_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PYROPE_BLOCK = registerBlock("pyrope_block",
            p -> new Block(p
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CRIMSON_GARNET_BLOCK = registerBlockWithStrength("crimson_garnet_block", Block::new);
    public static final DeferredBlock<Block> CRYSTALLITE_BLOCK = registerBlockWithStrength("crystallite_block", Block::new);
    public static final DeferredBlock<Block> RADIANT_AMETHYST_BLOCK = registerBlockWithStrength("radiant_amethyst_block", Block::new);
    public static final DeferredBlock<Block> MOONSTONE_BLOCK = registerBlockWithStrength("moonstone_block", Block::new);
    public static final DeferredBlock<Block> LIMESTONE_BLOCK = registerBlockWithStrength("limestone_block", Block::new);
    public static final DeferredBlock<Block> QUARTSIDIAN_BLOCK = registerBlockWithStrength("quartsidian_block", Block::new);
    public static final DeferredBlock<Block> ALEXANDRITE_BLOCK = registerBlockWithStrength("alexandrite_block", Block::new);
    public static final DeferredBlock<Block> ORANGE_ZIRCON_BLOCK = registerBlockWithStrength("orange_zircon_block", Block::new);
    public static final DeferredBlock<Block> OPAL_BLOCK = registerBlockWithStrength("opal_block", Block::new);
    public static final DeferredBlock<Block> GRANDIDIERITE_BLOCK = registerBlockWithStrength("grandidierite_block", Block::new);
    public static final DeferredBlock<Block> RED_BERYL_BLOCK = registerBlockWithStrength("red_beryl_block", Block::new);
    public static final DeferredBlock<Block> KASHMIR_SAPPHIRE_BLOCK = registerBlockWithStrength("kashmir_sapphire_block", Block::new);
    
    public static final DeferredBlock<Block> GEM_PURIFIER_BLOCK = registerBlock("gem_purifier_block",
            p -> new GemPurifierBlock(p.requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<Block> GEM_CRYSTALLIZER_BLOCK = registerBlock("gem_crystallizer_block", 
            p -> new GemCrystallizerBlock(p.requiresCorrectToolForDrops().noOcclusion()));
    
    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(6.0f, 6.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(6.5f, 6.5f).strength(6.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(5.0f, 5.0f).strength(5.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(5.5f, 5.5f).strength(5.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> GREEN_SAPPHIRE_ORE = registerBlock("green_sapphire_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(5.0f, 5.0f).strength(5.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_GREEN_SAPPHIRE_ORE = registerBlock("deepslate_green_sapphire_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(5.5f, 5.5f).strength(5.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> BLUE_GARNET_ORE = registerBlock("blue_garnet_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(7.0f, 7.5f).strength(8.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_BLUE_GARNET_ORE = registerBlock("deepslate_blue_garnet_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(7.5f, 8.0f).strength(8.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> PINK_GARNET_ORE = registerBlock("pink_garnet_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(7.0f, 7.5f).strength(8.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_PINK_GARNET_ORE = registerBlock("deepslate_pink_garnet_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(7.5f, 8.0f).strength(8.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> GREEN_GARNET_ORE = registerBlock("green_garnet_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(7.0f, 7.5f).strength(8.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_GREEN_GARNET_ORE = registerBlock("deepslate_green_garnet_ore", p -> new DropExperienceBlock(UniformInt.of(2, 3), p.requiresCorrectToolForDrops().strength(7.5f, 8.0f).strength(8.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> KYAWTHUITE_ORE = registerBlock("kyawthuite_ore", p -> new DropExperienceBlock(UniformInt.of(1, 2), p.requiresCorrectToolForDrops().strength(7.5f, 8f).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> DEEPSLATE_KYAWTHUITE_ORE = registerBlock("deepslate_kyawthuite_ore", p -> new DropExperienceBlock(UniformInt.of(1, 2), p.requiresCorrectToolForDrops().mapColor(MapColor.COLOR_ORANGE).strength(8f, 8.5f)));
    public static final DeferredBlock<Block> TOPAZ_ORE = registerBlock("topaz_ore", p -> new DropExperienceBlock(UniformInt.of(1, 3), p.requiresCorrectToolForDrops().strength(9.0f, 9.0f).strength(10.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore", p -> new DropExperienceBlock(UniformInt.of(1, 3), p.requiresCorrectToolForDrops().strength(9.5f, 9.5f).strength(10.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> WHITE_TOPAZ_ORE = registerBlock("white_topaz_ore", p -> new DropExperienceBlock(UniformInt.of(3, 5), p.requiresCorrectToolForDrops().strength(9.5f, 9.5f).strength(10.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_WHITE_TOPAZ_ORE = registerBlock("deepslate_white_topaz_ore", p -> new DropExperienceBlock(UniformInt.of(3, 5), p.requiresCorrectToolForDrops().strength(10.0f, 10.0f).strength(11.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> PERIDOT_ORE = registerBlock("peridot_ore", p -> new DropExperienceBlock(UniformInt.of(1, 2), p.requiresCorrectToolForDrops().strength(9.0f, 9.0f).strength(10.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_PERIDOT_ORE = registerBlock("deepslate_peridot_ore", p -> new DropExperienceBlock(UniformInt.of(1, 2), p.requiresCorrectToolForDrops().strength(9.5f, 9.5f).strength(10.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> JADE_ORE = registerBlock("jade_ore", p -> new DropExperienceBlock(UniformInt.of(3, 5), p.requiresCorrectToolForDrops().strength(10.0f, 10.0f).strength(11.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_JADE_ORE = registerBlock("deepslate_jade_ore", p -> new DropExperienceBlock(UniformInt.of(3, 5), p.requiresCorrectToolForDrops().strength(10.5f, 10.5f).strength(11.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> PYROPE_ORE = registerBlock("pyrope_ore", p -> new DropExperienceBlock(UniformInt.of(3, 5), p.requiresCorrectToolForDrops().strength(9.5f, 9.5f).strength(10.5f).sound(SoundType.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_PYROPE_ORE = registerBlock("deepslate_pyrope_ore", p -> new DropExperienceBlock(UniformInt.of(3, 5), p.requiresCorrectToolForDrops().strength(10.0f, 10.0f).strength(11.0f).sound(SoundType.STONE).mapColor(MapColor.STONE)));

    public static final DeferredBlock<Block> ECLIPSE_GEM_ORE = registerBlock("eclipse_gem_ore", s -> new Block(s.mapColor(MapColor.STONE).sound(SoundType.STONE).strength(16f, 16f)));
    
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static <T extends Block> DeferredBlock<T> registerBlockWithStrength(String id, Function<BlockBehaviour.Properties, T> blockFactory) {
        DeferredBlock<T> returnBlock = BLOCKS.registerBlock(id, blockFactory, () -> BlockBehaviour.Properties.of().strength(7f, 7f));
        registerBlockItem(id, returnBlock);
        return returnBlock;
    }
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        int count = 0;
        for (Block block: BuiltInRegistries.BLOCK) {
            Identifier identifier = BuiltInRegistries.BLOCK.getKey(block);
            if(identifier.getNamespace().equals(MoreOresModLoader.MOD_ID)) {
                count++;
                MoreOresModLoader.LOGGER.info("Registering block: {}", block);
            }
        }
        MoreOresModLoader.LOGGER.info("Registered {} blocks", count);
        MoreOresModLoader.LOGGER.info("Loading ModBlocks for" + MoreOresModLoader.MOD_ID + " mod.");
    }
}