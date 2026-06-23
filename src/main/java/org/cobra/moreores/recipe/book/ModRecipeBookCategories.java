package org.cobra.moreores.recipe.book;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.cobra.moreores.MoreOresModLoader;

public class ModRecipeBookCategories {

    public static final RecipeBookCategory GEM_POLISHING = register("gem_polishing");
    public static final RecipeBookCategory GEM_CRYSTALLIZER = register("gem_crystallizer");

    public static RecipeBookCategory register(String id) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, MoreOresModLoader.id(id), new RecipeBookCategory());
    }

    public static void register() {
        MoreOresModLoader.LOGGER.info("Loading ModRecipeBookCategory for " + MoreOresModLoader.MOD_ID + " mod.");
    }

}
