package org.cobra.moreores.item.util.impl;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;

import java.util.function.Supplier;

public enum CrystallizationGemstones implements IGemstone, StringRepresentable {
    EMPTY("empty", () -> new Item[]{Items.AIR}),
    CRIMSON_GARNET("crimson_garnet", () -> new Item[]{ModItems.CRIMSON_GARNET.get(), ModBlocks.CRIMSON_GARNET_BLOCK.get().asItem()}),
    RADIANT_AMETHYST("radiant_amethyst", () -> new Item[]{ModItems.RADIANT_AMETHYST.get(), ModBlocks.RADIANT_AMETHYST_BLOCK.get().asItem()}),
    CRYSTALLITE("crystallite", () -> new Item[]{ModItems.CRYSTALLITE.get(), ModBlocks.CRYSTALLITE_BLOCK.get().asItem()}),
    ALEXANDRITE("alexandrite", () -> new Item[]{ModItems.ALEXANDRITE.get(), ModBlocks.ALEXANDRITE_BLOCK.get().asItem()}),
    LIMESTONE("limestone", () -> new Item[]{ModItems.LIMESTONE.get(), ModBlocks.LIMESTONE_BLOCK.get().asItem()}),
    MOONSTONE("moonstone", () -> new Item[]{ModItems.MOONSTONE.get(), ModBlocks.MOONSTONE_BLOCK.get().asItem()}),
    QUARTSIDIAN("quartsidian", () -> new Item[]{ModItems.QUARTSIDIAN.get(), ModBlocks.QUARTSIDIAN_BLOCK.get().asItem()}),
    ORANGE_ZIRCON("orange_zircon", () -> new Item[]{ModItems.ORANGE_ZIRCON.get(), ModBlocks.ORANGE_ZIRCON_BLOCK.get().asItem()}),
    OPAL("opal", () -> new Item[]{ModItems.OPAL.get(), ModBlocks.OPAL_BLOCK.get().asItem()}),
    GRANDIDIERITE("grandidierite", () -> new Item[]{ModItems.GRANDIDIERITE.get(), ModBlocks.GRANDIDIERITE_BLOCK.get().asItem()}),
    RED_BERYL("red_beryl", () -> new Item[]{ModItems.RED_BERYL.get(), ModBlocks.RED_BERYL_BLOCK.get().asItem()}),
    KASHMIR_SAPPHIRE("kashmir_sapphire", () -> new Item[]{ModItems.KASHMIR_SAPPHIRE.get(), ModBlocks.KASHMIR_SAPPHIRE_BLOCK.get().asItem()});

    private final String name;
    private final Supplier<Item[]> items;

    public static final Codec<CrystallizationGemstones> CODEC = StringRepresentable.fromValues(CrystallizationGemstones::values);

    CrystallizationGemstones(String name, Supplier<Item[]> items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GemCategory category() {
        return GemCategory.CRYSTALLIZATION;
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
