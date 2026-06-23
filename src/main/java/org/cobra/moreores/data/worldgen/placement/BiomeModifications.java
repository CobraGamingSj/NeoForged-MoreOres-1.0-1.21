package org.cobra.moreores.data.worldgen.placement;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.cobra.moreores.MoreOresModLoader;

public class BiomeModifications {

    public static final ResourceKey<BiomeModifier> RUBY = of("ruby");
    public static final ResourceKey<BiomeModifier> RUBY_MED = of("ruby_med");
    public static final ResourceKey<BiomeModifier> RUBY_LARGE = of("ruby_large");
    public static final ResourceKey<BiomeModifier> SAPPHIRE = of("sapphire");
    public static final ResourceKey<BiomeModifier> SAPPHIRE_MED = of("sapphire_med");
    public static final ResourceKey<BiomeModifier> SAPPHIRE_LARGE = of("sapphire_large");
    public static final ResourceKey<BiomeModifier> GREEN_SAPPHIRE = of("green_sapphire");
    public static final ResourceKey<BiomeModifier> GREEN_SAPPHIRE_MED = of("green_sapphire_med");
    public static final ResourceKey<BiomeModifier> GREEN_SAPPHIRE_LARGE = of("green_sapphire_large");
    public static final ResourceKey<BiomeModifier> BLUE_GARNET = of("blue_garnet");
    public static final ResourceKey<BiomeModifier> BLUE_GARNET_MED = of("blue_garnet_med");
    public static final ResourceKey<BiomeModifier> BLUE_GARNET_LARGE = of("blue_garnet_large");
    public static final ResourceKey<BiomeModifier> PINK_GARNET = of("pink_garnet");
    public static final ResourceKey<BiomeModifier> PINK_GARNET_MED = of("pink_garnet_med");
    public static final ResourceKey<BiomeModifier> PINK_GARNET_LARGE = of("pink-garnet_large");
    public static final ResourceKey<BiomeModifier> GREEN_GARNET = of("green_garnet");
    public static final ResourceKey<BiomeModifier> GREEN_GARNET_MED = of("green_garnet_med");
    public static final ResourceKey<BiomeModifier> GREEN_GARNET_LARGE = of("green_garnet_large");
    public static final ResourceKey<BiomeModifier> TOPAZ = of("topaz");
    public static final ResourceKey<BiomeModifier> TOPAZ_MED = of("topaz_med");
    public static final ResourceKey<BiomeModifier> TOPAZ_LARGE = of("topaz_large");
    public static final ResourceKey<BiomeModifier> WHITE_TOPAZ = of("white_topaz");
    public static final ResourceKey<BiomeModifier> WHITE_TOPAZ_MED = of("white_topaz_med");
    public static final ResourceKey<BiomeModifier> WHITE_TOPAZ_LARGE = of("white_topaz_large");
    public static final ResourceKey<BiomeModifier> KYAWTHUITE = of("kyawthuite");
    public static final ResourceKey<BiomeModifier> KYAWTHUITE_DEEPSLATE = of("kyawthuite_deepslate");
    public static final ResourceKey<BiomeModifier> PERIDOT = of("peridot");
    public static final ResourceKey<BiomeModifier> PERIDOT_MED = of("peridot_med");
    public static final ResourceKey<BiomeModifier> PERIDOT_LARGE = of("peridot_large");
    public static final ResourceKey<BiomeModifier> JADE = of("jade");
    public static final ResourceKey<BiomeModifier> JADE_MED = of("jade_med");
    public static final ResourceKey<BiomeModifier> JADE_LARGE = of("jade_large");
    public static final ResourceKey<BiomeModifier> PYROPE = of("pyrope");
    public static final ResourceKey<BiomeModifier> PYROPE_MED = of("pyrope_med");
    public static final ResourceKey<BiomeModifier> PYROPE_LARGE = of("pyrope_large");
    
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        registerOre(context, RUBY, ModPlacedFeatures.ORE_RUBY);
        registerOre(context, RUBY_MED, ModPlacedFeatures.ORE_RUBY_MEDIUM);
        registerOre(context, RUBY_LARGE, ModPlacedFeatures.ORE_RUBY_LARGE);
        registerOre(context, SAPPHIRE, ModPlacedFeatures.ORE_SAPPHIRE);
        registerOre(context, SAPPHIRE_MED, ModPlacedFeatures.ORE_SAPPHIRE_MEDIUM);
        registerOre(context, SAPPHIRE_LARGE, ModPlacedFeatures.ORE_SAPPHIRE_LARGE);
        registerOre(context, GREEN_SAPPHIRE, ModPlacedFeatures.ORE_GREEN_SAPPHIRE);
        registerOre(context, GREEN_SAPPHIRE_MED, ModPlacedFeatures.ORE_GREEN_SAPPHIRE_MEDIUM);
        registerOre(context, GREEN_SAPPHIRE_LARGE, ModPlacedFeatures.ORE_GREEN_SAPPHIRE_LARGE);
        registerOre(context, BLUE_GARNET, ModPlacedFeatures.ORE_BLUE_GARNET);
        registerOre(context, BLUE_GARNET_MED, ModPlacedFeatures.ORE_BLUE_GARNET_MEDIUM);
        registerOre(context, BLUE_GARNET_LARGE, ModPlacedFeatures.ORE_BLUE_GARNET_LARGE);
        registerOre(context, PINK_GARNET, ModPlacedFeatures.ORE_PINK_GARNET);
        registerOre(context, PINK_GARNET_MED, ModPlacedFeatures.ORE_PINK_GARNET_MEDIUM);
        registerOre(context, PINK_GARNET_LARGE, ModPlacedFeatures.ORE_PINK_GARNET_LARGE);
        registerOre(context, GREEN_GARNET, ModPlacedFeatures.ORE_GREEN_GARNET);
        registerOre(context, GREEN_GARNET_MED, ModPlacedFeatures.ORE_GREEN_GARNET_MEDIUM);
        registerOre(context, GREEN_GARNET_LARGE, ModPlacedFeatures.ORE_GREEN_GARNET_LARGE);
        registerOre(context, TOPAZ, ModPlacedFeatures.ORE_TOPAZ);
        registerOre(context, TOPAZ_MED, ModPlacedFeatures.ORE_TOPAZ_MEDIUM);
        registerOre(context, TOPAZ_LARGE, ModPlacedFeatures.ORE_TOPAZ_LARGE);
        registerOre(context, WHITE_TOPAZ, ModPlacedFeatures.ORE_WHITE_TOPAZ);
        registerOre(context, WHITE_TOPAZ_MED, ModPlacedFeatures.ORE_WHITE_TOPAZ_MEDIUM);
        registerOre(context, WHITE_TOPAZ_LARGE, ModPlacedFeatures.ORE_WHITE_TOPAZ_LARGE);
        registerOre(context, KYAWTHUITE, ModPlacedFeatures.ORE_KYAWTHUITE);
        registerOre(context, KYAWTHUITE_DEEPSLATE, ModPlacedFeatures.ORE_KYAWTHUITE_DEEPSLATE);
        registerOre(context, PERIDOT, ModPlacedFeatures.ORE_PERIDOT);
        registerOre(context, PERIDOT_MED, ModPlacedFeatures.ORE_PERIDOT_MEDIUM);
        registerOre(context, PERIDOT_LARGE, ModPlacedFeatures.ORE_PERIDOT_LARGE);
        registerOre(context, JADE, ModPlacedFeatures.ORE_JADE);
        registerOre(context, JADE_MED, ModPlacedFeatures.ORE_JADE_MEDIUM);
        registerOre(context, JADE_LARGE, ModPlacedFeatures.ORE_JADE_LARGE);
        registerOre(context, PYROPE, ModPlacedFeatures.ORE_PYROPE);
        registerOre(context, PYROPE_MED, ModPlacedFeatures.ORE_PYROPE_MEDIUM);
        registerOre(context, PYROPE_LARGE, ModPlacedFeatures.ORE_PYROPE_LARGE);
    }
    
    private static ResourceKey<BiomeModifier> of(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MoreOresModLoader.id(name));
    }
    
    private static void registerOre(BootstrapContext<BiomeModifier> context, ResourceKey<BiomeModifier> key, ResourceKey<PlacedFeature> feature) {
        context.register(key, new BiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(feature)), 
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
