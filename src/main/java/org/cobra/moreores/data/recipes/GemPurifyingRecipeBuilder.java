package org.cobra.moreores.data.recipes;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.recipe.GemPurifierRecipe;

public class GemPurifyingRecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStackTemplate output;
    private final RecipeCategory category;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();

    public GemPurifyingRecipeBuilder(Ingredient ingredient, ItemStackTemplate output, RecipeCategory category) {
        this.ingredient = ingredient;
        this.output = output;
        this.category = category;
    }

    public static GemPurifyingRecipeBuilder create(Ingredient ingredient, ItemStackTemplate result, RecipeCategory category) {
        return new GemPurifyingRecipeBuilder(ingredient, result, category);
    }

    public GemPurifyingRecipeBuilder unlocks(String name, Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    public void save(RecipeOutput output, String id) {
        this.save(output, MoreOresModLoader.recipeKey(id + "_polishing"));
    }
    
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> recipeId) {
        GemPurifierRecipe gemPolishingRecipe = new GemPurifierRecipe(this.ingredient, this.output);
        output.accept(recipeId, gemPolishingRecipe, this.advancementBuilder.build(output, recipeId, this.category));
    }
}
