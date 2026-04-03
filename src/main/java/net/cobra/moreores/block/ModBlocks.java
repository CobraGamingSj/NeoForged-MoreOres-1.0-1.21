package net.cobra.moreores.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreOresModLoader.MOD_ID);

    public static final DeferredBlock<Block> ENERGY_BLOCK = register("energy_block",
            () -> new EnergyBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "energy_block")))
                    .strength(256f).strength(512f, 256f)));
    public static final DeferredBlock<Block> RUBY_BLOCK = register("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby_block")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RADIANT_BLOCK = register("radiant_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "radiant_block")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = register("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "sapphire_block")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GREEN_SAPPHIRE_BLOCK = register("green_sapphire_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "green_sapphire_block")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLUE_GARNET_BLOCK = register("blue_garnet_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "v")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_BLUE).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> PINK_GARNET_BLOCK = register("pink_garnet_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "pink_garnet_block")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_PINK).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> GREEN_GARNET_BLOCK = register("green_garnet_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "green_garnet_block")))
                    .strength(5f).strength(5f, 5f).mapColor(MapColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));


    public static final DeferredBlock<Block> RUBY_ORE = register("ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5), BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "ruby_ore")))
                    .strength(5.5f).strength(5.5f, 5.5f).mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "deepslate_ruby_ore")))
                    .strength(6f).strength(6f, 6f).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GEM_POLISHER_BLOCK = register("gem_polisher_block",
            () -> new GemPolisherBlock(BlockBehaviour.Properties.of().noOcclusion().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "gem_polisher_block")))));


    private static <T extends Block> DeferredBlock<T> register(String id, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(id, block);
        registerBlockItem(id, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String id, DeferredBlock<T> block) {
        ModItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, id))).useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        MoreOresModLoader.LOGGER.info("Loading ModBlocks for" + MoreOresModLoader.MOD_ID + " mod.");
    }
}