package org.cobra.moreores.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOres;

public class ModRecipeType {

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, MoreOres.MOD_ID);
    
    public static final DeferredHolder<RecipeType<?>, RecipeType<GemPurifierRecipe>> GEM_OURIFIER =
            TYPES.register("crystallizing", () -> new RecipeType<GemPurifierRecipe>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });

    public static void register(IEventBus schoolBus) {
        TYPES.register(schoolBus);
    }
    
}
