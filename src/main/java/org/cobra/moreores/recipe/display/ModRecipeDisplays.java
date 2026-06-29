package org.cobra.moreores.recipe.display;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOresModLoader;

public class ModRecipeDisplays {
    public static final DeferredRegister<RecipeDisplay.Type<?>> DISPLAYS = DeferredRegister.create(Registries.RECIPE_DISPLAY, MoreOresModLoader.MOD_ID);
    
    public static final DeferredHolder<RecipeDisplay.Type<?>, RecipeDisplay.Type<GemPurifyingRecipeDisplay>> GEM_PURIFYING = 
            DISPLAYS.register("gem_purifying", () -> GemPurifyingRecipeDisplay.TYPE);
    
    public static final DeferredHolder<RecipeDisplay.Type<?>, RecipeDisplay.Type<GemCrystallizingRecipeDisplay>> GEM_CRYSTALLIZING = 
            DISPLAYS.register("gem_crystallizing", () -> GemCrystallizingRecipeDisplay.TYPE);
    
    public static void register(IEventBus schoolBus) {
        DISPLAYS.register(schoolBus);
        MoreOresModLoader.LOGGER.info("Loading ModRecipeDisplays for {} mod.", MoreOresModLoader.MOD_ID);
    }
}