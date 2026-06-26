package org.cobra.moreores.data.recipes;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.recipe.GemCrystallizerRecipe;

import java.util.LinkedHashMap;
import java.util.Map;

public class GemCrystallizerRecipeBuilder {
    private final Ingredient ingredientBefore;
    private final Ingredient ingredientAfter;
    private final ItemStackTemplate output;
    private final RecipeCategory category;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();

    public GemCrystallizerRecipeBuilder(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStackTemplate output, RecipeCategory category) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = output;
        this.category = category;
    }

    public static GemCrystallizerRecipeBuilder create(Ingredient ingredientBefore, ItemStackTemplate result, RecipeCategory category) {
        return new GemCrystallizerRecipeBuilder(ingredientBefore, Ingredient.of(ModItems.RADIANT), result, category);
    }

    public static GemCrystallizerRecipeBuilder createQuartsidian() {
        return new GemCrystallizerRecipeBuilder(Ingredient.of(Items.QUARTZ), Ingredient.of(Blocks.OBSIDIAN.asItem()), new ItemStackTemplate(ModItems.QUARTSIDIAN.get()), RecipeCategory.MISC);
    }

    public GemCrystallizerRecipeBuilder unlocks(String name, Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    public void save(RecipeOutput output, String id) {
        this.save(output, MoreOresModLoader.recipeKey(id + "_crystallizing"));
    }
    
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> recipeId) {
        GemCrystallizerRecipe gemcrystallizerRecipe = new GemCrystallizerRecipe(this.ingredientBefore, this.ingredientAfter, this.output);
        output.accept(recipeId, gemcrystallizerRecipe, advancementBuilder.build(output, recipeId, this.category));
    }
}
