package org.cobra.moreores;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.cobra.moreores.data.AutomaticModelCreator;
import org.cobra.moreores.data.AutomaticRecipeCreator;
import org.cobra.moreores.data.AutomaticTranslationCreator;
import org.cobra.moreores.data.DataPackCreator;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MoreOresModLoader.MOD_ID)
public class MoreOresDataGenerator {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> providerCompletableFuture = event.getLookupProvider();
        
        generator.addProvider(true, new AutomaticModelCreator(output));
        generator.addProvider(true, new DataPackCreator(output, providerCompletableFuture));
        generator.addProvider(true, new AutomaticTranslationCreator(output));
        generator.addProvider(true, new AutomaticRecipeCreator.Runner(output, providerCompletableFuture));
    }
}
