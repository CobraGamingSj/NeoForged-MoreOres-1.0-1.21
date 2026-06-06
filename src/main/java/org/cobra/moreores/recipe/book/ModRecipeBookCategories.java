package org.cobra.moreores.recipe.book;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.cobra.moreores.MoreOres;

public class ModRecipeBookCategories {

    public static final RecipeBookCategory GEM_POLISHING = register("gem_polishing");
    public static final RecipeBookCategory GEM_CRYSTALLIZER = register("gem_crystallizer");

    public static RecipeBookCategory register(String id) {
        return Registry.register(Registries.RECIPE_BOOK_CATEGORY, MoreOres.id(id), new RecipeBookCategory());
    }

    public static void register() {
        MoreOres.LOGGER.info("Loading ModRecipeBookCategory for " + MoreOres.MOD_ID + " mod.");
    }

}
