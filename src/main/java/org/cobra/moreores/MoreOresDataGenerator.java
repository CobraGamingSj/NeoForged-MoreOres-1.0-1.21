package org.cobra.moreores;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.cobra.moreores.data.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MoreOresModLoader.MOD_ID)
public class MoreOresDataGenerator {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> providerCompletableFuture = event.getLookupProvider();
        
        generator.addProvider(true, new AutomatedModelCreator(output));
        generator.addProvider(true, new DataPackCreator(output, providerCompletableFuture));
        generator.addProvider(true, new AutomatedTranslationKeyCreator(output));
        generator.addProvider(true, new AutomatedRecipeCreator.Runner(output, providerCompletableFuture));
        generator.addProvider(true, new ItemTagGen(output, providerCompletableFuture));
        generator.addProvider(true, new BlockTagGen(output, providerCompletableFuture));
        generator.addProvider(true, new VillagerTradeTagGen(output, providerCompletableFuture));
        generator.addProvider(true, new PointOfInterestTypeTagGen(output, providerCompletableFuture));
        generator.addProvider(true, new LootTableProvider(output, Collections.emptySet(), 
                List.of(new LootTableProvider.SubProviderEntry(AutomatedBlockLootCreator::new, LootContextParamSets.BLOCK)), providerCompletableFuture));
    }
}
