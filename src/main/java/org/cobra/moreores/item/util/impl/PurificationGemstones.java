package org.cobra.moreores.item.util.impl;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;

import java.util.function.Supplier;

public enum PurificationGemstones implements IGemstone, StringRepresentable {
    EMPTY("empty", () -> new Item[]{Items.AIR}),
    RUBY("ruby", () -> new Item[]{ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.get().asItem()}),
    SAPPHIRE("sapphire", () -> new Item[]{ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get().asItem()}),
    GREEN_SAPPHIRE("green_sapphire", () -> new Item[]{ModItems.GREEN_SAPPHIRE.get(), ModBlocks.GREEN_SAPPHIRE_BLOCK.get().asItem()}),
    BLUE_GARNET("blue_garnet", () -> new Item[]{ModItems.BLUE_GARNET.get(), ModBlocks.BLUE_GARNET_BLOCK.get().asItem()}),
    PINK_GARNET("pink_garnet", () -> new Item[]{ModItems.PINK_GARNET.get(), ModBlocks.PINK_GARNET_BLOCK.get().asItem()}),
    GREEN_GARNET("green_garnet", () -> new Item[]{ModItems.GREEN_GARNET.get(), ModBlocks.GREEN_GARNET_BLOCK.get().asItem()}),
    KYAWTHUITE("kyawthuite", () -> new Item[]{ModItems.KYAWTHUITE.get(), ModBlocks.KYAWTHUITE_BLOCK.get().asItem()}),
    TOPAZ("topaz", () -> new Item[]{ModItems.TOPAZ.get(), ModBlocks.TOPAZ_BLOCK.get().asItem()}),
    WHITE_TOPAZ("white_topaz", () -> new Item[]{ModItems.WHITE_TOPAZ.get(), ModBlocks.WHITE_TOPAZ_BLOCK.get().asItem()}),
    PERIDOT("peridot", () -> new Item[]{ModItems.PERIDOT.get(), ModBlocks.PERIDOT_BLOCK.get().asItem()}),
    JADE("jade", () -> new Item[]{ModItems.JADE.get(), ModBlocks.JADE_BLOCK.get().asItem()}),
    PYROPE("pyrope", () -> new Item[]{ModItems.PYROPE.get(), ModBlocks.PYROPE_BLOCK.get().asItem()});
    
    private final String name;
    private final Supplier<Item[]> items;

    public static final Codec<PurificationGemstones> CODEC = StringRepresentable.fromValues(PurificationGemstones::values);
    
    PurificationGemstones(String name, Supplier<Item[]> items) {
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
    public Supplier<Item[]> items() {
        return items;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
