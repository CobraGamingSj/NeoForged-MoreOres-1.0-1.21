package net.cobra.moreores.datagen;

import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class RecipeGen extends RecipeProvider implements IConditionBuilder {

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new RecipeGen(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Recipe Gen!";
        }
    }

    public RecipeGen(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        trimSmithing(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "guardian")));
    }
}
