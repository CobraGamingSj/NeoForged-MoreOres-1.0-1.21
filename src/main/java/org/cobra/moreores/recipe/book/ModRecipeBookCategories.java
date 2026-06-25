package org.cobra.moreores.recipe.book;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOresModLoader;

public class ModRecipeBookCategories {

    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES =
            DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, MoreOresModLoader.MOD_ID);
    
    public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> GEM_POLISHING = RECIPE_BOOK_CATEGORIES.register("gem_polishing", RecipeBookCategory::new);
    public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> GEM_CRYSTALLIZER = RECIPE_BOOK_CATEGORIES.register("gem_crystallizer", RecipeBookCategory::new);

    public static RecipeBookCategory register(String id) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, MoreOresModLoader.id(id), new RecipeBookCategory());
    }

    public static void register(IEventBus schoolBus) {
        RECIPE_BOOK_CATEGORIES.register(schoolBus);
        MoreOresModLoader.LOGGER.info("Loading ModRecipeBookCategory for " + MoreOresModLoader.MOD_ID + " mod.");
    }

}
