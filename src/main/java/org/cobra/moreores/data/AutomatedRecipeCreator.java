package org.cobra.moreores.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.data.recipes.GemCrystallizerRecipeBuilder;
import org.cobra.moreores.data.recipes.GemPurifyingRecipeBuilder;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.equipment.trim.ModTrimPatterns;
import org.cobra.moreores.registry.ModItemTags;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.packs.VanillaRecipeProvider.smithingTrims;

public class AutomatedRecipeCreator extends RecipeProvider {
    private static final Map<Item, SmithingData> SMITHING_DATA = Map.ofEntries(
            Map.entry(Items.NETHERITE_SWORD, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_SWORD.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_PICKAXE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_PICKAXE.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_AXE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_AXE.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_HOE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_HOE.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_SHOVEL, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_SHOVEL.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_HELMET, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_HELMET.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_CHESTPLATE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_CHESTPLATE.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_LEGGINGS, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_LEGGINGS.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_BOOTS, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_BOOTS.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_NAUTILUS_ARMOR, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_NAUTILUS_ARMOR.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_SPEAR, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RUBY_SPEAR.get(), ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_SWORD.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_SWORD.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_PICKAXE.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_PICKAXE.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_AXE.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_AXE.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_HOE.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_HOE.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_SHOVEL.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_SHOVEL.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_HELMET.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_HELMET.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_CHESTPLATE.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_CHESTPLATE.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_LEGGINGS.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_LEGGINGS.get(), ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_BOOTS.get(), new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.RADIANT_BOOTS.get(), ModItemTags.RADIANT_TOOL_MATERIALS))
    );
    
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

    private static final Map<Item, Item> GEM_POLISHABLES = Map.ofEntries(
            Map.entry(ModItems.RAW_RUBY.get(), ModItems.RUBY.get()),
            Map.entry(ModBlocks.RAW_RUBY_BLOCK.get().asItem(), ModBlocks.RUBY_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_SAPPHIRE.get(), ModItems.SAPPHIRE.get()),
            Map.entry(ModBlocks.RAW_SAPPHIRE_BLOCK.get().asItem(), ModBlocks.SAPPHIRE_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_GREEN_SAPPHIRE.get(), ModItems.GREEN_SAPPHIRE.get()),
            Map.entry(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK.get().asItem(), ModBlocks.GREEN_SAPPHIRE_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_BLUE_GARNET.get(), ModItems.BLUE_GARNET.get()),
            Map.entry(ModBlocks.RAW_BLUE_GARNET_BLOCK.get().asItem(), ModBlocks.BLUE_GARNET_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_PINK_GARNET.get(), ModItems.PINK_GARNET.get()),
            Map.entry(ModBlocks.RAW_PINK_GARNET_BLOCK.get().asItem(), ModBlocks.PINK_GARNET_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_GREEN_GARNET.get(), ModItems.GREEN_GARNET.get()),
            Map.entry(ModBlocks.RAW_GREEN_GARNET_BLOCK.get().asItem(), ModBlocks.GREEN_GARNET_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_KYAWTHUITE.get(), ModItems.KYAWTHUITE.get()),
            Map.entry(ModBlocks.RAW_KYAWTHUITE_BLOCK.get().asItem(), ModBlocks.KYAWTHUITE_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_TOPAZ.get(), ModItems.TOPAZ.get()),
            Map.entry(ModBlocks.RAW_TOPAZ_BLOCK.get().asItem(), ModBlocks.TOPAZ_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_WHITE_TOPAZ.get(), ModItems.WHITE_TOPAZ.get()),
            Map.entry(ModBlocks.RAW_WHITE_TOPAZ_BLOCK.get().asItem(), ModBlocks.WHITE_TOPAZ_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_PERIDOT.get(), ModItems.PERIDOT.get()),
            Map.entry(ModBlocks.RAW_PERIDOT_BLOCK.get().asItem(), ModBlocks.PERIDOT_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_JADE.get(), ModItems.JADE.get()),
            Map.entry(ModBlocks.RAW_JADE_BLOCK.get().asItem(), ModBlocks.JADE_BLOCK.get().asItem()),
            Map.entry(ModItems.RAW_PYROPE.get(), ModItems.PYROPE.get()),
            Map.entry(ModBlocks.RAW_PYROPE_BLOCK.get().asItem(), ModBlocks.PYROPE_BLOCK.get().asItem())
    );
//
    private static final Map<Item, Item> GEM_CRYSTALLIZABLES = Map.ofEntries(
            Map.entry(ModItems.RUBY.get(), ModItems.ALEXANDRITE.get()),
            Map.entry(ModBlocks.RUBY_BLOCK.get().asItem(),  ModBlocks.ALEXANDRITE_BLOCK.get().asItem()),
            Map.entry(ModItems.SAPPHIRE.get(), ModItems.KASHMIR_SAPPHIRE.get()),
            Map.entry(ModBlocks.SAPPHIRE_BLOCK.get().asItem(), ModBlocks.KASHMIR_SAPPHIRE_BLOCK.get().asItem()),
            Map.entry(ModItems.GREEN_SAPPHIRE.get(), ModItems.CRYSTALLITE.get()),
            Map.entry(ModBlocks.GREEN_SAPPHIRE_BLOCK.get().asItem(), ModBlocks.CRYSTALLITE_BLOCK.get().asItem()),
            Map.entry(ModItems.BLUE_GARNET.get(), ModItems.CRIMSON_GARNET.get()),
            Map.entry(ModBlocks.BLUE_GARNET_BLOCK.get().asItem(), ModBlocks.CRIMSON_GARNET_BLOCK.get().asItem()),
            Map.entry(ModItems.PINK_GARNET.get(), ModItems.RADIANT_AMETHYST.get()),
            Map.entry(ModBlocks.PINK_GARNET_BLOCK.get().asItem(), ModBlocks.RADIANT_AMETHYST_BLOCK.get().asItem()),
            Map.entry(ModItems.GREEN_GARNET.get(), ModItems.LIMESTONE.get()),
            Map.entry(ModBlocks.GREEN_GARNET_BLOCK.get().asItem(), ModBlocks.LIMESTONE_BLOCK.get().asItem()),
            Map.entry(ModItems.KYAWTHUITE.get(), ModItems.ORANGE_ZIRCON.get()),
            Map.entry(ModBlocks.KYAWTHUITE_BLOCK.get().asItem(), ModBlocks.ORANGE_ZIRCON_BLOCK.get().asItem()),
            Map.entry(ModItems.WHITE_TOPAZ.get(), ModItems.MOONSTONE.get()),
            Map.entry(ModBlocks.WHITE_TOPAZ_BLOCK.get().asItem(), ModBlocks.MOONSTONE_BLOCK.get().asItem()),
            Map.entry(ModItems.PERIDOT.get(), ModItems.OPAL.get()),
            Map.entry(ModBlocks.PERIDOT_BLOCK.get().asItem(), ModBlocks.OPAL_BLOCK.get().asItem()),
            Map.entry(ModItems.JADE.get(), ModItems.GRANDIDIERITE.get()),
            Map.entry(ModBlocks.JADE_BLOCK.get().asItem(), ModBlocks.GRANDIDIERITE_BLOCK.get().asItem()),
            Map.entry(ModItems.PYROPE.get(), ModItems.RED_BERYL.get()),
            Map.entry(ModBlocks.PYROPE_BLOCK.get().asItem(), ModBlocks.RED_BERYL_BLOCK.get().asItem())
    );
    
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
    
    public AutomatedRecipeCreator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        int defaultSmeltingTime = 1500;
        int defaultBlastingTime = 750;

        for (Map.Entry<Item, Item> entry: SMELTABLES.entrySet()) {
            var define = entry.getKey();
            var output = entry.getValue();

            oreSmelting(List.of(define), RecipeCategory.MISC, CookingBookCategory.BLOCKS, output, .15f, defaultSmeltingTime, output.toString());
            oreBlasting(List.of(define), RecipeCategory.MISC, CookingBookCategory.BLOCKS, output, .15f, defaultBlastingTime, output.toString());
        }

        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);

            if(id.getNamespace().equals(MoreOresModLoader.MOD_ID)) {
                if(block.defaultBlockState().is(ModBlocks.GEM_CRYSTALLIZER_BLOCK.get()) || block.defaultBlockState().is(ModBlocks.GEM_PURIFIER_BLOCK.get())) {
                    continue;
                }
                String path = id.getPath();
                if(path.endsWith("_block")) {
                    String itemName = path.replace("_block", "");
                    Item item = BuiltInRegistries.ITEM.getValue(MoreOresModLoader.id(itemName));
                    if(block.defaultBlockState().is(ModBlocks.RADIANT_BLOCK.get())) {
                        shaped(RecipeCategory.MISC, ModItems.RADIANT.get(), 1)
                                .pattern("aaa")
                                .pattern("aba")
                                .pattern("aaa")
                                .define('a', ModBlocks.RUBY_BLOCK.get())
                                .define('b', Items.DIAMOND)
                                .unlockedBy(getHasName(ModBlocks.RUBY_BLOCK.get()), has(ModBlocks.RUBY_BLOCK.get()))
                                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.RADIANT) + "_from_ruby"));
                        reversibleCompactingRecipe(RecipeCategory.MISC, ModItems.RADIANT.get(), RecipeCategory.MISC, ModBlocks.RADIANT_BLOCK.get());
                        continue;
                    }
                    if(block.defaultBlockState().is(ModBlocks.ENERGY_BLOCK.get())) {
                        shaped(RecipeCategory.MISC, ModBlocks.ENERGY_BLOCK.get(), 1)
                                .pattern("aaa")
                                .pattern("aba")
                                .pattern("aaa")
                                .define('a', ModItems.RADIANT.get())
                                .define('b', Blocks.TNT)
                                .unlockedBy(getHasName(ModItems.RADIANT.get()), has(ModItems.RADIANT.get()))
                                .unlockedBy(getHasName(Blocks.TNT), has(Blocks.TNT))
                                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModBlocks.ENERGY_BLOCK.get()) + "_from_radiant"));

                        shapeless(RecipeCategory.MISC, ModItems.ENERGY_INGOT.get(), 9)
                                .unlockedBy(getHasName(ModBlocks.ENERGY_BLOCK.get()), has(ModItems.ENERGY_INGOT.get()))
                                .requires(ModItems.ENERGY_INGOT.get())
                                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.ENERGY_INGOT.get())));
                        continue;
                    }

                    reversibleCompactingRecipe(RecipeCategory.BUILDING_BLOCKS, item, RecipeCategory.DECORATIONS, block);
                }
            }
        }

        for (Map.Entry<Item, Item> entry : GEM_POLISHABLES.entrySet()) {
            Item input = entry.getKey();
            Item result = entry.getValue();

            gemPurification(Ingredient.of(input), result)
                    .unlocks(getHasName(input), has(input))
                    .save(output, getSimpleRecipeName(input));
        }
        
        for (Map.Entry<Item, Item> entry : GEM_CRYSTALLIZABLES.entrySet()) {
            Item input = entry.getKey();
            Item result = entry.getValue();
            
            gemCrystallization(Ingredient.of(input), result)
                    .unlocks(getHasName(input), has(input))
                    .unlocks(getHasName(ModItems.RADIANT.get()), has(ModItems.RADIANT.get()))
                    .save(output, getSimpleRecipeName(input));
        }
        
        for (Map.Entry<Item, SmithingData> entry : SMITHING_DATA.entrySet()) {
            Item baseItem = entry.getKey();
            SmithingData data = entry.getValue();
            Item result = data.result();
            Item template = data.smithingTemplate();
            TagKey<Item> tag = data.toolTag();
            String path = BuiltInRegistries.ITEM.getKey(result).getPath();
            RecipeCategory category = (path.contains("_pickaxe") || path.contains("hoe") || path.contains("_shovel"))
                    ? RecipeCategory.TOOLS : RecipeCategory.COMBAT;
            SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(baseItem), tag(tag), category, result)
                    .unlocks(getHasName(ModItems.RUBY.get()), has(tag))
                    .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(result) + "_smithing"));
        }
        
        for(Map.Entry<Item, Item> entry : SAPPHIRE_MAP.entrySet()) {
            Item defineItem = entry.getKey();
            Item outputItem = entry.getValue();
            String path = BuiltInRegistries.ITEM.getKey(outputItem).getPath();
            RecipeCategory category = (path.contains("_pickaxe") || path.contains("hoe") || path.contains("_shovel"))
                    ? RecipeCategory.TOOLS : RecipeCategory.COMBAT;
            shaped(category, outputItem)
                    .pattern("aaa")
                    .pattern("aba")
                    .pattern("aaa")
                    .define('a', ModItems.SAPPHIRE.get())
                    .define('b', defineItem)
                    .unlockedBy(getHasName(ModItems.SAPPHIRE.get()),  has(ModItems.SAPPHIRE.get()))
                    .unlockedBy(getHasName(defineItem), has(defineItem))
                    .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(outputItem)));
        }
        
        oreBlasting(List.of(ModItems.RUBY), RecipeCategory.MISC, CookingBookCategory.MISC, Items.NETHERITE_INGOT, 0.15f, 450, "netherite");

        shaped(RecipeCategory.MISC, ModBlocks.GEM_PURIFIER_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("B B")
                .define('I', Blocks.IRON_BLOCK)
                .define('B', Blocks.IRON_BARS)
                .unlockedBy(getHasName(Blocks.IRON_BLOCK), has(Blocks.IRON_BLOCK))
                .unlockedBy(getHasName(Blocks.IRON_BARS), has(Blocks.IRON_BARS))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModBlocks.GEM_PURIFIER_BLOCK.get())));

        shaped(
                RecipeCategory.REDSTONE, ModBlocks.GEM_CRYSTALLIZER_BLOCK.get()
        )
                .pattern("aba")
                .pattern("cdc")
                .pattern("ccc")
                .define('a', Items.REDSTONE)
                .define('b', Ingredient.of(ModItems.ENERGY_INGOT.get(), ModBlocks.ENERGY_BLOCK.get().asItem()))
                .define('c', Ingredient.of(Blocks.IRON_BLOCK.asItem()))
                .define('d', Ingredient.of(ModBlocks.GEM_PURIFIER_BLOCK.get().asItem()))
                .unlockedBy(getHasName(ModBlocks.GEM_PURIFIER_BLOCK.get().asItem()), has(ModBlocks.GEM_PURIFIER_BLOCK.get().asItem()))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModBlocks.GEM_CRYSTALLIZER_BLOCK.get())));

        trimSmithing(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get(),
                ModTrimPatterns.GUARDIAN, MoreOresModLoader.recipeKey(getItemName(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get()) + "_smithing_trim"));
        
        GemCrystallizerRecipeBuilder.createQuartsidian()
                .unlocks(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                .unlocks(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(output, getSimpleRecipeName(ModItems.QUARTSIDIAN));

        shaped(RecipeCategory.REDSTONE, ModBlocks.RUBY_LAMP.get(), 1)
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', Items.REDSTONE)
                .define('b', ModItems.RUBY.get())
                .define('c', Blocks.GLOWSTONE)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .unlockedBy(getHasName(Blocks.GLOWSTONE), has(Blocks.GLOWSTONE))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModBlocks.RUBY_LAMP.get())));

        shaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .pattern("aba")
                .pattern("aca")
                .pattern("aaa")
                .define('a', ModItems.RUBY.get())
                .define('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get())
                .define('c', Blocks.STONE)
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get()) + "_duplication"));

        shaped(RecipeCategory.MISC, ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .pattern("aba")
                .pattern("aca")
                .pattern("aaa")
                .define('a', ModItems.SAPPHIRE.get())
                .define('b', ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get())
                .define('c', ModBlocks.RUBY_BLOCK.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get()) + "_duplication"));

        shaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get())
                .pattern("aba")
                .pattern("aca")
                .pattern("aaa")
                .define('a', Blocks.STONE)
                .define('b', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .define('c', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .unlockedBy(getHasName(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get())));

        shaped(RecipeCategory.MISC, ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get())
                .pattern("aba")
                .pattern("aca")
                .pattern("aaa")
                .define('a', ModBlocks.RUBY_BLOCK.get())
                .define('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get())
                .define('c', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .unlockedBy(getHasName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get()), has(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get())));

        shaped(RecipeCategory.MISC, ModItems.ECLIPSE_GEM)
                .pattern("abc")
                .pattern("def")
                .pattern("ghi")
                .define('a', ModItems.RADIANT_AMETHYST)
                .define('b', ModItems.MOONSTONE)
                .define('c', ModItems.LIMESTONE)
                .define('d', ModItems.QUARTSIDIAN)
                .define('e', ModItems.CRYSTAL_OF_ECLIPSE)
                .define('f', ModItems.ALEXANDRITE)
                .define('g', ModItems.ORANGE_ZIRCON)
                .define('h', ModItems.OPAL)
                .define('i', ModItems.GRANDIDIERITE)
                .unlockedBy(getHasName(ModItems.CRYSTAL_OF_ECLIPSE), has(ModItems.CRYSTAL_OF_ECLIPSE))
                .save(output, MoreOresModLoader.recipeKey(getSimpleRecipeName(ModItems.ECLIPSE_GEM)));

        shaped(RecipeCategory.MISC, ModItems.RADIANT.get())
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .define('a', ModItems.RUBY.get())
                .define('b', Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(output, getSimpleRecipeName(ModItems.RADIANT_DUST.get()) + "_from_ruby");
    }

    public void reversibleCompactingRecipe(
            RecipeCategory reverseCategory, ItemLike baseItem, RecipeCategory compactingCategory, ItemLike compactItem
    ) {
        this.reversibleCompactingRecipe(
                reverseCategory, baseItem, compactingCategory, compactItem, getSimpleRecipeName(compactItem), null, getSimpleRecipeName(baseItem), null
        );
    }
    
    public final void reversibleCompactingRecipe(
            RecipeCategory reverseCategory,
            ItemLike baseItem,
            RecipeCategory compactingCategory,
            ItemLike compactItem,
            String compactingId,
            @Nullable String compactingGroup,
            String reverseId,
            @Nullable String reverseGroup
    ) {
        this.shapeless(reverseCategory, baseItem, 9)
                .requires(compactItem)
                .group(reverseGroup)
                .unlockedBy(getHasName(compactItem), this.has(compactItem))
                .save(this.output, ResourceKey.create(Registries.RECIPE, MoreOresModLoader.id(reverseId)));
        this.shaped(compactingCategory, compactItem)
                .define('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(compactingGroup)
                .unlockedBy(getHasName(baseItem), this.has(baseItem))
                .save(this.output, ResourceKey.create(Registries.RECIPE, MoreOresModLoader.id(compactingId)));
    }

    public GemPurifyingRecipeBuilder gemPurification(Ingredient input, Item result) {
        return GemPurifyingRecipeBuilder.create(input, new ItemStackTemplate(result), RecipeCategory.MISC);
    }

    public GemCrystallizerRecipeBuilder gemCrystallization(Ingredient inputBefore, Item result) {
        return GemCrystallizerRecipeBuilder.create(inputBefore, new ItemStackTemplate(result), RecipeCategory.MISC);
    }
    
    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new AutomatedRecipeCreator(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "ARC";
        }
    }
    
    private record SmithingData(Item smithingTemplate, Item result, TagKey<Item> toolTag) {
        
    }
}
