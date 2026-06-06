package org.cobra.moreores.item.util.impl;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;

public enum PurifyingGemstones implements IGem, StringRepresentable {
    NONE("empty", Items.AIR),
    RUBY("ruby", ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.get().asItem()),
    SAPPHIRE("sapphire", ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get().asItem()),
    GREEN_SAPPHIRE("green_sapphire", ModItems.GREEN_SAPPHIRE.get(), ModBlocks.GREEN_SAPPHIRE_BLOCK.get().asItem()),
    BLUE_GARNET("blue_garnet", ModItems.BLUE_GARNET.get(), ModBlocks.BLUE_GARNET_BLOCK.get().asItem()),
    PINK_GARNET("pink_garnet", ModItems.PINK_GARNET.get(), ModBlocks.PINK_GARNET_BLOCK.get().asItem()),
    GREEN_GARNET("green_garnet", ModItems.GREEN_GARNET.get(), ModBlocks.GREEN_GARNET_BLOCK.get().asItem()),
    KYAWTHUITE("kyawthuite", ModItems.KYAWTHUITE, ModBlocks.KYAWTHUITE_BLOCK.get().asItem()),
    TOPAZ("topaz", ModItems.TOPAZ, ModBlocks.TOPAZ_BLOCK.get().asItem()),
    WHITE_TOPAZ("white_topaz", ModItems.WHITE_TOPAZ, ModBlocks.WHITE_TOPAZ_BLOCK.get().asItem()),
    PERIDOT("peridot", ModItems.PERIDOT, ModBlocks.PERIDOT_BLOCK.get().asItem()),
    JADE("jade", ModItems.JADE, ModBlocks.JADE_BLOCK.get().asItem()),
    PYROPE("pyrope", ModItems.PYROPE, ModBlocks.PYROPE_BLOCK.get().asItem());
    
    private final String name;
    private final Item[] items;

    public static final Codec<PurifyingGemstones> CODEC = StringRepresentable.fromValues(PurifyingGemstones::values);
    
    PurifyingGemstones(String name, Item... items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GemCategory category() {
        return GemCategory.PURIFYING;
    }

    @Override
    public Item[] items() {
        return items;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
