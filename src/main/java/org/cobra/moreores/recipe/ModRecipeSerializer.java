package org.cobra.moreores.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.cobra.moreores.MoreOresModLoader;

public class ModRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MoreOresModLoader.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GemPurifierRecipe>> GEM_PURIFIER =
            SERIALIZERS.register("gem_purifying", () -> new RecipeSerializer<>(GemPurifierRecipe.CODEC, GemPurifierRecipe.STREAM_CODEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GemCrystallizerRecipe>> GEM_CRYSTALLIZER =
            SERIALIZERS.register("gem_crystallizing", () -> new RecipeSerializer<>(GemCrystallizerRecipe.CODEC, GemCrystallizerRecipe.STREAM_CODEC));

    public static void register(IEventBus schoolBus) {
        SERIALIZERS.register(schoolBus);
    }
    
}
