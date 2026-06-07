//package org.cobra.moreores.item.util.impl;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.util.StringRepresentable;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.Items;
//import org.cobra.moreores.block.ModBlocks;
//import org.cobra.moreores.item.ModItems;
//import org.cobra.moreores.item.util.GemCategory;
//
//public enum CrystallizationGemstones implements IGem, StringRepresentable {
//    NONE("empty", Items.AIR),
//    CRIMSON_GARNET("crimson_garnet", ModItems.CRIMSON_GARNET.get(), ModBlocks.CRIMSON_GARNET_BLOCK.get().asItem()),
//    RADIANT_AMETHYST("radiant_amethyst", ModItems.RADIANT_AMETHYST.get(), ModBlocks.RADIANT_AMETHYST_BLOCK.get().asItem()),
//    CRYSTALLITE("crystallite", ModItems.CRYSTALLITE.get(), ModBlocks.CRYSTALLITE_BLOCK.get().asItem()),
//    ALEXANDRITE("alexandrite", ModItems.ALEXANDRITE.get(), ModBlocks.ALEXANDRITE_BLOCK.get().asItem()),
//    LIMESTONE("limestone", ModItems.LIMESTONE.get(), ModBlocks.LIMESTONE_BLOCK.get().asItem()),
//    MOONSTONE("moonstone", ModItems.MOONSTONE.get(), ModBlocks.MOONSTONE_BLOCK.get().asItem()),
//    QUARTSIDIAN("quartsidian", ModItems.QUARTSIDIAN.get(), ModBlocks.QUARTSIDIAN_BLOCK.get().asItem()),
//    ORANGE_ZIRCON("orange_zircon", ModItems.ORANGE_ZIRCON.get(), ModBlocks.ORANGE_ZIRCON_BLOCK.get().asItem()),
//    OPAL("opal", ModItems.OPAL.get(), ModBlocks.OPAL_BLOCK.get().asItem()),
//    GRANDIDIERITE("grandidierite", ModItems.GRANDIDIERITE.get(), ModBlocks.GRANDIDIERITE_BLOCK.get().asItem()),
//    RED_BERYL("red_beryl", ModItems.RED_BERYL.get(), ModBlocks.RED_BERYL_BLOCK.get().asItem()),
//    KASHMIR_SAPPHIRE("kashmir_sapphire", ModItems.KASHMIR_SAPPHIRE.get(), ModBlocks.KASHMIR_SAPPHIRE_BLOCK.get().asItem());
//    
//    private final String name;
//    private final Item[] items;
//
//    public static final Codec<CrystallizationGemstones> CODEC = StringRepresentable.fromValues(CrystallizationGemstones::values);
//    
//    CrystallizationGemstones(String name, Item... items) {
//        this.name = name;
//        this.items = items;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public GemCategory category() {
//        return GemCategory.CRYSTALLIZATION;
//    }
//
//    @Override
//    public Item[] items() {
//        return items;
//    }
//
//    @Override
//    public String getSerializedName() {
//        return name;
//    }
//}
