package org.cobra.moreores.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AutomaticRecipeCreator extends RecipeProvider {
    
    private static final Map<Item, Item> SMELTABLES = Map.ofEntries(
            Map.entry(ModBlocks.RUBY_ORE.get().asItem(), ModItems.RUBY.get()),
            Map.entry(ModBlocks.DEEPSLATE_RUBY_ORE.get().asItem(), ModItems.RUBY.get()),
            Map.entry(ModBlocks.SAPPHIRE_ORE.get().asItem(), ModItems.SAPPHIRE.get()),
            Map.entry(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().asItem(), ModItems.SAPPHIRE.get()),
            Map.entry(ModBlocks.GREEN_SAPPHIRE_ORE.get().asItem(), ModItems.GREEN_SAPPHIRE.get()),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE.get().asItem(), ModItems.GREEN_SAPPHIRE.get()),
            Map.entry(ModBlocks.BLUE_GARNET_ORE.get().asItem(), ModItems.BLUE_GARNET.get()),
            Map.entry(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE.get().asItem(), ModItems.BLUE_GARNET.get()),
            Map.entry(ModBlocks.PINK_GARNET_ORE.get().asItem(), ModItems.PINK_GARNET.get()),
            Map.entry(ModBlocks.DEEPSLATE_PINK_GARNET_ORE.get().asItem(), ModItems.PINK_GARNET.get()),
            Map.entry(ModBlocks.GREEN_GARNET_ORE.get().asItem(), ModItems.GREEN_GARNET.get()),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE.get().asItem(), ModItems.GREEN_GARNET.get()),
            Map.entry(ModBlocks.KYAWTHUITE_ORE.get().asItem(), ModItems.KYAWTHUITE.get()),
            Map.entry(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE.get().asItem(), ModItems.KYAWTHUITE.get()),
            Map.entry(ModBlocks.TOPAZ_ORE.get().asItem(), ModItems.TOPAZ.get()),
            Map.entry(ModBlocks.DEEPSLATE_TOPAZ_ORE.get().asItem(), ModItems.TOPAZ.get()),
            Map.entry(ModBlocks.WHITE_TOPAZ_ORE.get().asItem(), ModItems.WHITE_TOPAZ.get()),
            Map.entry(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE.get().asItem(), ModItems.WHITE_TOPAZ.get()),
            Map.entry(ModBlocks.PERIDOT_ORE.get().asItem(), ModItems.PERIDOT.get()),
            Map.entry(ModBlocks.DEEPSLATE_PERIDOT_ORE.get().asItem(), ModItems.PERIDOT.get()),
            Map.entry(ModBlocks.JADE_ORE.get().asItem(), ModItems.JADE.get()),
            Map.entry(ModBlocks.DEEPSLATE_JADE_ORE.get().asItem(), ModItems.JADE.get()),
            Map.entry(ModBlocks.PYROPE_ORE.get().asItem(), ModItems.PYROPE.get()),
            Map.entry(ModBlocks.DEEPSLATE_PYROPE_ORE.get().asItem(), ModItems.PYROPE.get()),
            Map.entry(ModItems.RAW_RUBY.get(), ModItems.RUBY.get()),
            Map.entry(ModItems.RAW_SAPPHIRE.get(), ModItems.SAPPHIRE.get()),
            Map.entry(ModItems.RAW_GREEN_SAPPHIRE.get(), ModItems.GREEN_SAPPHIRE.get()),
            Map.entry(ModItems.RAW_BLUE_GARNET.get(), ModItems.BLUE_GARNET.get()),
            Map.entry(ModItems.RAW_PINK_GARNET.get(), ModItems.PINK_GARNET.get()),
            Map.entry(ModItems.RAW_GREEN_GARNET.get(), ModItems.GREEN_GARNET.get()),
            Map.entry(ModItems.RAW_KYAWTHUITE.get(), ModItems.KYAWTHUITE.get()),
            Map.entry(ModItems.RAW_TOPAZ.get(), ModItems.TOPAZ.get()),
            Map.entry(ModItems.RAW_WHITE_TOPAZ.get(), ModItems.WHITE_TOPAZ.get()),
            Map.entry(ModItems.RAW_PERIDOT.get(), ModItems.PERIDOT.get()),
            Map.entry(ModItems.RAW_JADE.get(), ModItems.JADE.get()),
            Map.entry(ModItems.RAW_PYROPE.get(), ModItems.PYROPE.get())
    );

//    private static final Map<Item, Item> GEM_POLISHABLES = Map.ofEntries(
//            Map.entry(ModItems.RAW_RUBY, ModItems.RUBY),
//            Map.entry(ModBlocks.RAW_RUBY_BLOCK.asItem(),  ModBlocks.RUBY_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_SAPPHIRE, ModItems.SAPPHIRE),
//            Map.entry(ModBlocks.RAW_SAPPHIRE_BLOCK.asItem(), ModBlocks.SAPPHIRE_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_GREEN_SAPPHIRE, ModItems.GREEN_SAPPHIRE),
//            Map.entry(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK.asItem(), ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_BLUE_GARNET, ModItems.BLUE_GARNET),
//            Map.entry(ModBlocks.RAW_BLUE_GARNET_BLOCK.asItem(), ModBlocks.BLUE_GARNET_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_PINK_GARNET, ModItems.PINK_GARNET),
//            Map.entry(ModBlocks.RAW_PINK_GARNET_BLOCK.asItem(), ModBlocks.PINK_GARNET_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_GREEN_GARNET, ModItems.GREEN_GARNET),
//            Map.entry(ModBlocks.RAW_GREEN_GARNET_BLOCK.asItem(), ModBlocks.GREEN_GARNET_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_KYAWTHUITE, ModItems.KYAWTHUITE),
//            Map.entry(ModBlocks.RAW_KYAWTHUITE_BLOCK.asItem(), ModBlocks.KYAWTHUITE_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_TOPAZ, ModItems.TOPAZ),
//            Map.entry(ModBlocks.RAW_TOPAZ_BLOCK.asItem(), ModBlocks.TOPAZ_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_WHITE_TOPAZ, ModItems.WHITE_TOPAZ),
//            Map.entry(ModBlocks.RAW_WHITE_TOPAZ_BLOCK.asItem(), ModBlocks.WHITE_TOPAZ_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_PERIDOT, ModItems.PERIDOT),
//            Map.entry(ModBlocks.RAW_PERIDOT_BLOCK.asItem(), ModBlocks.PERIDOT_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_JADE, ModItems.JADE),
//            Map.entry(ModBlocks.RAW_JADE_BLOCK.asItem(), ModBlocks.JADE_BLOCK.asItem()),
//            Map.entry(ModItems.RAW_PYROPE, ModItems.PYROPE),
//            Map.entry(ModBlocks.RAW_PYROPE_BLOCK.asItem(), ModBlocks.PYROPE_BLOCK.asItem())
//    );
//
//    private static final Map<Item, Item> GEM_INFUSES = Map.ofEntries(
//            Map.entry(ModItems.RUBY, ModItems.ALEXANDRITE),
//            Map.entry(ModBlocks.RUBY_BLOCK.asItem(),  ModBlocks.ALEXANDRITE_BLOCK.asItem()),
//            Map.entry(ModItems.SAPPHIRE, ModItems.KASHMIR_SAPPHIRE),
//            Map.entry(ModBlocks.SAPPHIRE_BLOCK.asItem(), ModBlocks.KASHMIR_SAPPHIRE_BLOCK.asItem()),
//            Map.entry(ModItems.GREEN_SAPPHIRE, ModItems.CRYSTALLITE),
//            Map.entry(ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem(), ModBlocks.CRYSTALLITE_BLOCK.asItem()),
//            Map.entry(ModItems.BLUE_GARNET, ModItems.CRIMSON_GARNET),
//            Map.entry(ModBlocks.BLUE_GARNET_BLOCK.asItem(), ModBlocks.CRIMSON_GARNET_BLOCK.asItem()),
//            Map.entry(ModItems.PINK_GARNET, ModItems.RADIANT_AMETHYST),
//            Map.entry(ModBlocks.PINK_GARNET_BLOCK.asItem(), ModBlocks.RADIANT_AMETHYST_BLOCK.asItem()),
//            Map.entry(ModItems.GREEN_GARNET, ModItems.LIMESTONE),
//            Map.entry(ModBlocks.GREEN_GARNET_BLOCK.asItem(), ModBlocks.LIMESTONE_BLOCK.asItem()),
//            Map.entry(ModItems.KYAWTHUITE, ModItems.ORANGE_ZIRCON),
//            Map.entry(ModBlocks.KYAWTHUITE_BLOCK.asItem(), ModBlocks.ORANGE_ZIRCON_BLOCK.asItem()),
//            Map.entry(ModItems.WHITE_TOPAZ, ModItems.MOONSTONE),
//            Map.entry(ModBlocks.WHITE_TOPAZ_BLOCK.asItem(), ModBlocks.MOONSTONE_BLOCK.asItem()),
//            Map.entry(ModItems.PERIDOT, ModItems.OPAL),
//            Map.entry(ModBlocks.PERIDOT_BLOCK.asItem(), ModBlocks.OPAL_BLOCK.asItem()),
//            Map.entry(ModItems.JADE, ModItems.GRANDIDIERITE),
//            Map.entry(ModBlocks.JADE_BLOCK.asItem(), ModBlocks.GRANDIDIERITE_BLOCK.asItem()),
//            Map.entry(ModItems.PYROPE, ModItems.RED_BERYL),
//            Map.entry(ModBlocks.PYROPE_BLOCK.asItem(), ModBlocks.RED_BERYL_BLOCK.asItem())
//    );
    
    private static final  Map<Item, Item> SAPPHIRE_MAP = Map.ofEntries(
            Map.entry(ModItems.RUBY_SWORD.get(), ModItems.SAPPHIRE_SWORD.get()),
            Map.entry(ModItems.RUBY_PICKAXE.get(), ModItems.SAPPHIRE_PICKAXE.get()),
            Map.entry(ModItems.RUBY_AXE.get(), ModItems.SAPPHIRE_AXE.get()),
            Map.entry(ModItems.RUBY_SHOVEL.get(), ModItems.SAPPHIRE_SHOVEL.get()),
            Map.entry(ModItems.RUBY_HOE.get(), ModItems.SAPPHIRE_HOE.get()),
            Map.entry(ModItems.RUBY_SPEAR.get(), ModItems.SAPPHIRE_SPEAR.get()),
            Map.entry(ModItems.RUBY_HELMET.get(), ModItems.SAPPHIRE_HELMET.get()),
            Map.entry(ModItems.RUBY_CHESTPLATE.get(), ModItems.SAPPHIRE_CHESTPLATE.get()),
            Map.entry(ModItems.RUBY_LEGGINGS.get(), ModItems.SAPPHIRE_LEGGINGS.get()),
            Map.entry(ModItems.RUBY_BOOTS.get(), ModItems.SAPPHIRE_BOOTS.get()),
            Map.entry(ModItems.RUBY_NAUTILUS_ARMOR.get(), ModItems.SAPPHIRE_NAUTILUS_ARMOR.get())
    );
    
    public AutomaticRecipeCreator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        int defaultSmeltingTime = 1500;
        int defaultBlastingTime = 750;

        for (var entry: SMELTABLES.entrySet()) {
            var input = entry.getKey();
            var output = entry.getValue();

            oreSmelting(List.of(input), RecipeCategory.MISC, CookingBookCategory.BLOCKS, output, .15f, defaultSmeltingTime, output.toString());
            oreBlasting(List.of(input), RecipeCategory.MISC, CookingBookCategory.BLOCKS, output, .15f, defaultBlastingTime, output.toString());
        }

        for(Map.Entry<Item, Item> entry : SAPPHIRE_MAP.entrySet()) {
            Item inputItem = entry.getKey();
            Item outputItem = entry.getValue();
            String path = BuiltInRegistries.ITEM.getKey(outputItem).getPath();
            RecipeCategory category = (path.contains("_pickaxe") || path.contains("hoe") || path.contains("_shovel"))
                    ? RecipeCategory.TOOLS : RecipeCategory.COMBAT;
            shaped(category, outputItem)
                    .pattern("aaa")
                    .pattern("aba")
                    .pattern("aaa")
                    .define('a', ModItems.SAPPHIRE.get())
                    .define('b', inputItem)
                    .unlockedBy(getHasName(ModItems.SAPPHIRE.get()),  has(ModItems.SAPPHIRE.get()))
                    .unlockedBy(getHasName(inputItem), has(inputItem))
                    .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(outputItem)));
        }
    }
    
    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new AutomaticRecipeCreator(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "ARC";
        }
    }
}
