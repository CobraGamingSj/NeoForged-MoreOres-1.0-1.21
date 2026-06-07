package org.cobra.moreores.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOres;

public class ModRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MoreOres.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GemPurifierRecipe>> GEM_PURIFIER =
            SERIALIZERS.register("crystallizing", () -> new RecipeSerializer<>(GemPurifierRecipe.CODEC, GemPurifierRecipe.STREAM_CODEC));

    public static void register(IEventBus schoolBus) {
        SERIALIZERS.register(schoolBus);
    }
    
}
