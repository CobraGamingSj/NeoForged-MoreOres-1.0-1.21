package org.cobra.moreores.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.data.village.TradeSets;
import org.cobra.moreores.data.worldgen.placement.BiomeModifications;
import org.cobra.moreores.data.worldgen.placement.ModConfiguredFeatures;
import org.cobra.moreores.data.worldgen.placement.ModPlacedFeatures;
import org.cobra.moreores.enchantment.ModEnchantments;
import org.cobra.moreores.item.equipment.trim.ModTrimMaterials;
import org.cobra.moreores.item.equipment.trim.ModTrimPatterns;
import org.cobra.moreores.item.trading.ModVillagerTrades;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataPackCreator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap)
            .add(Registries.TRADE_SET, TradeSets::bootstrap)
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            .add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap)
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifications::bootstrap);
    
    public DataPackCreator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MoreOresModLoader.MOD_ID));
    }
}