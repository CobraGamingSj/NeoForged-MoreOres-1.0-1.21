package org.cobra.moreores.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.registry.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {
    public ItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreOresModLoader.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SPEARS)
                .add(ModItems.RUBY_SPEAR.get())
                .add(ModItems.SAPPHIRE_SPEAR.get());

        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.RUBY.get())
                .add(ModItems.RADIANT.get())
                .add(ModItems.SAPPHIRE.get())
                .add(ModItems.GREEN_SAPPHIRE.get())
                .add(ModItems.BLUE_GARNET.get())
                .add(ModItems.PINK_GARNET.get())
                .add(ModItems.GREEN_GARNET.get())
                .add(ModItems.KYAWTHUITE.get())
                .add(ModItems.TOPAZ.get())
                .add(ModItems.WHITE_TOPAZ.get())
                .add(ModItems.PERIDOT.get())
                .add(ModItems.JADE.get())
                .add(ModItems.PYROPE.get())
                .add(ModItems.CRIMSON_GARNET.get())
                .add(ModItems.CRYSTALLITE.get())
                .add(ModItems.ALEXANDRITE.get())
                .add(ModItems.OPAL.get())
                .add(ModItems.QUARTSIDIAN.get())
                .add(ModItems.RADIANT_AMETHYST.get())
                .add(ModItems.LIMESTONE.get())
                .add(ModItems.MOONSTONE.get())
                .add(ModItems.RED_BERYL.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get())
                .add(ModItems.RADIANT_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.RADIANT_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.RADIANT_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.RUBY_SHOVEL.get())
                .add(ModItems.SAPPHIRE_SHOVEL.get())
                .add(ModItems.RADIANT_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(ModItems.RUBY_HOE.get())
                .add(ModItems.SAPPHIRE_HOE.get())
                .add(ModItems.RADIANT_HOE.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.RUBY_BOOTS.get())
                .add(ModItems.SAPPHIRE_BOOTS.get())
                .add(ModItems.RADIANT_BOOTS.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.RADIANT_LEGGINGS.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.RADIANT_HELMET.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.RADIANT_CHESTPLATE.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.RUBY_BOOTS.get())
                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.RADIANT_HELMET.get())
                .add(ModItems.RADIANT_CHESTPLATE.get())
                .add(ModItems.RADIANT_LEGGINGS.get())
                .add(ModItems.RADIANT_BOOTS.get());

        tag(ModItemTags.GEMSTONE)
                .add(ModItems.RUBY.get())
                .add(ModItems.RADIANT.get())
                .add(ModItems.SAPPHIRE.get())
                .add(ModItems.GREEN_SAPPHIRE.get())
                .add(ModItems.BLUE_GARNET.get())
                .add(ModItems.PINK_GARNET.get())
                .add(ModItems.GREEN_GARNET.get())
                .add(ModItems.KYAWTHUITE.get())
                .add(ModItems.TOPAZ.get())
                .add(ModItems.WHITE_TOPAZ.get())
                .add(ModItems.PERIDOT.get())
                .add(ModItems.JADE.get())
                .add(ModItems.PYROPE.get())
                .add(Items.LAPIS_LAZULI)
                .add(Items.QUARTZ)
                .add(Items.DIAMOND)
                .add(ModItems.CRIMSON_GARNET.get(), ModItems.CRYSTALLITE.get(), ModItems.ALEXANDRITE.get(), ModItems.ORANGE_ZIRCON.get(),
                        ModItems.OPAL.get(), ModItems.QUARTSIDIAN.get(), ModItems.KASHMIR_SAPPHIRE.get(), ModItems.RADIANT_AMETHYST.get(),
                        ModItems.LIMESTONE.get(), ModItems.MOONSTONE.get(), ModItems.RED_BERYL.get(), ModItems.GRANDIDIERITE.get(), ModItems.CRYSTAL_OF_ECLIPSE.get());

        tag(ModItemTags.GEMSTONE_BLOCKS)
                .add(ModBlocks.RUBY_BLOCK.asItem(),
                        ModBlocks.SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.BLUE_GARNET_BLOCK.asItem(),
                        ModBlocks.PINK_GARNET_BLOCK.asItem(),
                        ModBlocks.GREEN_GARNET_BLOCK.asItem(),
                        ModBlocks.KYAWTHUITE_BLOCK.asItem(),
                        ModBlocks.TOPAZ_BLOCK.asItem(),
                        ModBlocks.WHITE_TOPAZ_BLOCK.asItem(),
                        ModBlocks.PERIDOT_BLOCK.asItem(),
                        ModBlocks.JADE_BLOCK.asItem(),
                        ModBlocks.PYROPE_BLOCK.asItem());

        tag(ModItemTags.RAW_GEMSTONE)
                .add(ModItems.RAW_RUBY.get())
                .add(ModItems.RAW_SAPPHIRE.get())
                .add(ModItems.RAW_GREEN_SAPPHIRE.get())
                .add(ModItems.RAW_BLUE_GARNET.get())
                .add(ModItems.RAW_PINK_GARNET.get())
                .add(ModItems.RAW_GREEN_GARNET.get())
                .add(ModItems.RAW_KYAWTHUITE.get())
                .add(ModItems.RAW_TOPAZ.get())
                .add(ModItems.RAW_WHITE_TOPAZ.get())
                .add(ModItems.RAW_PERIDOT.get())
                .add(ModItems.RAW_JADE.get())
                .add(ModItems.RAW_PYROPE.get());

        tag(ModItemTags.RAW_GEMSTONE_BLOCKS)
                .add(ModBlocks.RAW_RUBY_BLOCK.asItem(),
                        ModBlocks.RAW_SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.RAW_BLUE_GARNET_BLOCK.asItem(),
                        ModBlocks.RAW_PINK_GARNET_BLOCK.asItem(),
                        ModBlocks.RAW_GREEN_GARNET_BLOCK.asItem(),
                        ModBlocks.RAW_KYAWTHUITE_BLOCK.asItem(),
                        ModBlocks.RAW_TOPAZ_BLOCK.asItem(),
                        ModBlocks.RAW_WHITE_TOPAZ_BLOCK.asItem(),
                        ModBlocks.RAW_PERIDOT_BLOCK.asItem(),
                        ModBlocks.RAW_JADE_BLOCK.asItem(),
                        ModBlocks.RAW_PYROPE_BLOCK.asItem());

//        tag(ItemTags.ARROWS)
//                .add(ModItems.GEM_ARROW);
//
//        tag(ItemTags.BOW_ENCHANTABLE)
//                .add(ModItems.RADIANT_BOW);

        tag(ModItemTags.METAL)
                .add(Items.IRON_INGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT);

        tag(ModItemTags.RARE)
                .addTag(ModItemTags.GEMSTONE)
                .addTag(ModItemTags.METAL);

        tag(ModItemTags.REPAIRS_RUBY_ARMOR)
                .add(ModItems.RUBY.get());

        tag(ModItemTags.REPAIRS_SAPPHIRE_ARMOR)
                .add(ModItems.SAPPHIRE.get());

        tag(ModItemTags.REPAIRS_RADIANT_ARMOR)
                .add(ModItems.RADIANT.get());

        tag(ModItemTags.RUBY_TOOL_MATERIALS)
                .add(ModItems.RUBY.get());

        tag(ModItemTags.SAPPHIRE_TOOL_MATERIALS)
                .add(ModItems.SAPPHIRE.get());

        tag(ModItemTags.RADIANT_TOOL_MATERIALS)
                .add(ModItems.RADIANT.get());
    }
}
