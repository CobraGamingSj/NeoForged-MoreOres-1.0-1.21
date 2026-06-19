package org.cobra.moreores.item.util.impl;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;

import java.util.function.Supplier;

public enum PurifyingGemstones implements IGemstone, StringRepresentable {
    EMPTY("empty", () -> new Item[]{Items.AIR}),
    RUBY("ruby", () -> new Item[]{ModItems.RUBY.get()}),
    SAPPHIRE("sapphire", () -> new Item[]{ModItems.SAPPHIRE.get()}),
    GREEN_SAPPHIRE("green_sapphire", () -> new Item[]{ModItems.GREEN_SAPPHIRE.get()}),
    BLUE_GARNET("blue_garnet", () -> new Item[]{ModItems.BLUE_GARNET.get()}),
    PINK_GARNET("pink_garnet", () -> new Item[]{ModItems.PINK_GARNET.get()}),
    GREEN_GARNET("green_garnet", () -> new Item[]{ModItems.GREEN_GARNET.get()});
//    KYAWTHUITE("kyawthuite", ModItems.KYAWTHUITE, ModBlocks.KYAWTHUITE_BLOCK.get().asItem()),
//    TOPAZ("topaz", ModItems.TOPAZ, ModBlocks.TOPAZ_BLOCK.get().asItem()),
//    WHITE_TOPAZ("white_topaz", ModItems.WHITE_TOPAZ, ModBlocks.WHITE_TOPAZ_BLOCK.get().asItem()),
//    PERIDOT("peridot", ModItems.PERIDOT, ModBlocks.PERIDOT_BLOCK.get().asItem()),
//    JADE("jade", ModItems.JADE, ModBlocks.JADE_BLOCK.get().asItem()),
//    PYROPE("pyrope", ModItems.PYROPE, ModBlocks.PYROPE_BLOCK.get().asItem());
    
    private final String name;
    private final Supplier<Item[]> items;

    public static final Codec<PurifyingGemstones> CODEC = StringRepresentable.fromValues(PurifyingGemstones::values);
    
    PurifyingGemstones(String name, Supplier<Item[]> items) {
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
