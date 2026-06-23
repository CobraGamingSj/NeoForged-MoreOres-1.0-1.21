package org.cobra.moreores.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOresModLoader;

public class ModRecipeType {
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, MoreOresModLoader.MOD_ID);
    
    public static final DeferredHolder<RecipeType<?>, RecipeType<GemPurifierRecipe>> GEM_PURIFIER =
            TYPES.register("gem_purifying", () -> new RecipeType<GemPurifierRecipe>() {
                @Override
                public String toString() {
                    return "gem_purifying";
                }
            });

    public static void register(IEventBus schoolBus) {
        TYPES.register(schoolBus);
    }
}
