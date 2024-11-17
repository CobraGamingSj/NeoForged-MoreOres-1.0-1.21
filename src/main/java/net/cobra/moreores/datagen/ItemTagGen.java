package net.cobra.moreores.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.tags.ModTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {
    public ItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MoreOresModLoader.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.RUBY.get())
                .add(ModItems.RADIANT.get())
                .add(ModItems.SAPPHIRE.get())
                .add(ModItems.GREEN_SAPPHIRE.get())
                .add(ModItems.BLUE_GARNET.get())
                .add(ModItems.PINK_GARNET.get())
                .add(ModItems.GREEN_GARNET.get())
                .add(ModItems.TOPAZ.get())
                .add(ModItems.WHITE_TOPAZ.get())
                .add(ModItems.PERIDOT.get())
                .add(ModItems.JADE.get())
                .add(ModItems.PYROPE.get());

        tag(ModTags.Items.REPAIRS_RUBY_ARMOR)
                .add(ModItems.RUBY.get());

        tag(ModTags.Items.REPAIRS_SAPPHIRE_ARMOR)
                .add(ModItems.SAPPHIRE.get());

        tag(ModTags.Items.IS_GEMSTONE)
                .add(ModItems.RUBY.get())
                .add(ModItems.RADIANT.get())
                .add(ModItems.SAPPHIRE.get())
                .add(ModItems.GREEN_SAPPHIRE.get())
                .add(ModItems.BLUE_GARNET.get())
                .add(ModItems.PINK_GARNET.get())
                .add(ModItems.GREEN_GARNET.get())
                .add(ModItems.TOPAZ.get())
                .add(ModItems.WHITE_TOPAZ.get())
                .add(ModItems.PERIDOT.get())
                .add(ModItems.JADE.get())
                .add(ModItems.PYROPE.get());

        tag(ModTags.Items.IS_RARE)
                .add(ModItems.RUBY.get())
                .add(ModItems.RADIANT.get())
                .add(ModItems.SAPPHIRE.get())
                .add(ModItems.GREEN_SAPPHIRE.get())
                .add(ModItems.BLUE_GARNET.get())
                .add(ModItems.PINK_GARNET.get())
                .add(ModItems.GREEN_GARNET.get())
                .add(ModItems.TOPAZ.get())
                .add(ModItems.WHITE_TOPAZ.get())
                .add(ModItems.PERIDOT.get())
                .add(ModItems.JADE.get())
                .add(ModItems.PYROPE.get())
                .add(Items.DIAMOND)
                .add(Items.NETHERITE_INGOT);

        tag(ModTags.Items.IS_METAL)
                .add(Items.IRON_INGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT);

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.RUBY_BOOTS.get());

        tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get());
    }
}
