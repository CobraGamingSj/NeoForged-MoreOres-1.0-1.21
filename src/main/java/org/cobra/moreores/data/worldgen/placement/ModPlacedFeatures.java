package org.cobra.moreores.data.worldgen.placement;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.cobra.moreores.MoreOresModLoader;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ORE_RUBY = of("ore_ruby");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_MEDIUM = of("ore_ruby_medium");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_LARGE = of("ore_ruby_large");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = of("ore_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_MEDIUM = of("ore_sapphire_medium");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_LARGE = of("ore_sapphire_large");
    public static final ResourceKey<PlacedFeature> ORE_GREEN_SAPPHIRE = of("ore_green_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_GREEN_SAPPHIRE_MEDIUM = of("ore_green_sapphire_medium");
    public static final ResourceKey<PlacedFeature> ORE_GREEN_SAPPHIRE_LARGE = of("ore_green_sapphire_large");
    public static final ResourceKey<PlacedFeature> ORE_BLUE_GARNET = of("ore_blue_garnet");
    public static final ResourceKey<PlacedFeature> ORE_BLUE_GARNET_MEDIUM = of("ore_blue_garnet_medium");
    public static final ResourceKey<PlacedFeature> ORE_BLUE_GARNET_LARGE = of("ore_blue_garnet_large");
    public static final ResourceKey<PlacedFeature> ORE_PINK_GARNET = of("ore_pink_garnet");
    public static final ResourceKey<PlacedFeature> ORE_PINK_GARNET_MEDIUM = of("ore_pink_garnet_medium");
    public static final ResourceKey<PlacedFeature> ORE_PINK_GARNET_LARGE = of("ore_pink_garnet_large");
    public static final ResourceKey<PlacedFeature> ORE_GREEN_GARNET = of("ore_green_garnet");
    public static final ResourceKey<PlacedFeature> ORE_GREEN_GARNET_MEDIUM = of("ore_green_garnet_medium");
    public static final ResourceKey<PlacedFeature> ORE_GREEN_GARNET_LARGE = of("ore_green_garnet_large");
    public static final ResourceKey<PlacedFeature> ORE_KYAWTHUITE = of("ore_kyawthuite");
    public static final ResourceKey<PlacedFeature> ORE_KYAWTHUITE_DEEPSLATE = of("ore_kyawthuite_deepslate");
    public static final ResourceKey<PlacedFeature> ORE_TOPAZ = of("ore_topaz");
    public static final ResourceKey<PlacedFeature> ORE_TOPAZ_MEDIUM = of("ore_topaz_medium");
    public static final ResourceKey<PlacedFeature> ORE_TOPAZ_LARGE = of("ore_topaz_large");
    public static final ResourceKey<PlacedFeature> ORE_WHITE_TOPAZ = of("ore_white_topaz");
    public static final ResourceKey<PlacedFeature> ORE_WHITE_TOPAZ_MEDIUM = of("ore_white_topaz_medium");
    public static final ResourceKey<PlacedFeature> ORE_WHITE_TOPAZ_LARGE = of("ore_white_topaz_large");
    public static final ResourceKey<PlacedFeature> ORE_PERIDOT = of("ore_peridot");
    public static final ResourceKey<PlacedFeature> ORE_PERIDOT_MEDIUM = of("ore_peridot_medium");
    public static final ResourceKey<PlacedFeature> ORE_PERIDOT_LARGE = of("ore_peridot_large");
    public static final ResourceKey<PlacedFeature> ORE_JADE = of("ore_jade");
    public static final ResourceKey<PlacedFeature> ORE_JADE_MEDIUM = of("ore_jade_medium");
    public static final ResourceKey<PlacedFeature> ORE_JADE_LARGE = of("ore_jade_large");
    public static final ResourceKey<PlacedFeature> ORE_PYROPE = of("ore_pyrope");
    public static final ResourceKey<PlacedFeature> ORE_PYROPE_MEDIUM = of("ore_pyrope_medium");
    public static final ResourceKey<PlacedFeature> ORE_PYROPE_LARGE = of("ore_pyrope_large");
    public static final ResourceKey<PlacedFeature> ORE_ECLIPSE_GEM = of("ore_eclipse_gem");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        register(
                context,
                ORE_RUBY, configuredFeature(context, ModConfiguredFeatures.ORE_RUBY_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(76)))
        );
        register(
                context, ORE_RUBY_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_RUBY_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_RUBY_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_RUBY_LARGE),
                rareOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(72)))
        );
        register(
                context,
                ORE_SAPPHIRE,
                configuredFeature(context, ModConfiguredFeatures.ORE_SAPPHIRE_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(76)))
        );
        register(
                context, ORE_SAPPHIRE_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_SAPPHIRE_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_SAPPHIRE_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_SAPPHIRE_LARGE),
                rareOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(72)))
        );
        register(
                context,
                ORE_GREEN_SAPPHIRE,
                configuredFeature(context, ModConfiguredFeatures.ORE_GREEN_SAPPHIRE_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(76)))
        );
        register(
                context, ORE_GREEN_SAPPHIRE_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_GREEN_SAPPHIRE_MEDIUM),commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_GREEN_SAPPHIRE_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_GREEN_SAPPHIRE_LARGE),
                rareOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(77)))
        );
        register(
                context,
                ORE_BLUE_GARNET,
                configuredFeature(context, ModConfiguredFeatures.ORE_BLUE_GARNET_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(75)))
        );
        register(
                context, ORE_BLUE_GARNET_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_BLUE_GARNET_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_BLUE_GARNET_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_BLUE_GARNET_LARGE),
                rareOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(70)))
        );
        register(
                context,
                ORE_PINK_GARNET,
                configuredFeature(context, ModConfiguredFeatures.ORE_PINK_GARNET_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(70)))
        );
        register(
                context, ORE_PINK_GARNET_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_PINK_GARNET_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_PINK_GARNET_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_PINK_GARNET_LARGE),
                rareOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(75)))
        );
        register(
                context,
                ORE_GREEN_GARNET,
                configuredFeature(context, ModConfiguredFeatures.ORE_GREEN_GARNET_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(71)))
        );
        register(
                context, ORE_GREEN_GARNET_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_GREEN_GARNET_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_GREEN_GARNET_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_GREEN_GARNET_LARGE),
                rareOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(67)))
        );
        register(
                context,
                ORE_KYAWTHUITE,
                configuredFeature(context, ModConfiguredFeatures.ORE_KYAWTHUITE),
                List.of(
                        CountPlacement.of(2),
                        RarityFilter.onAverageOnceEvery(5), // very rare in stone
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-32),
                                VerticalAnchor.absolute(40)
                        ),
                        BiomeFilter.biome()
                )
        );
        register(
                context,
                ORE_KYAWTHUITE_DEEPSLATE,
                configuredFeature(context, ModConfiguredFeatures.ORE_KYAWTHUITE),
                List.of(
                        CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(5), // deepslate
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(-20)
                        ),
                        BiomeFilter.biome()
                )
        );
        register(
                context,
                ORE_TOPAZ,
                configuredFeature(context, ModConfiguredFeatures.ORE_TOPAZ_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(52)))
        );
        register(
                context, ORE_TOPAZ_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_TOPAZ_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_TOPAZ_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_TOPAZ_LARGE),
                rareOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(56)))
        );
        register(
                context,
                ORE_WHITE_TOPAZ,
                configuredFeature(context, ModConfiguredFeatures.ORE_WHITE_TOPAZ_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(67)))
        );
        register(
                context, ORE_WHITE_TOPAZ_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_WHITE_TOPAZ_MEDIUM),commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_WHITE_TOPAZ_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_WHITE_TOPAZ_LARGE),
                rareOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(68)))
        );
        register(
                context,
                ORE_PERIDOT,
                configuredFeature(context, ModConfiguredFeatures.ORE_PERIDOT_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(62)))
        );
        register(
                context, ORE_PERIDOT_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_PERIDOT_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_PERIDOT_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_PERIDOT_LARGE),
                rareOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(68)))
        );
        register(
                context,
                ORE_JADE,
                configuredFeature(context, ModConfiguredFeatures.ORE_JADE_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(73)))
        );
        register(
                context, ORE_JADE_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_JADE_MEDIUM), commonOrePlacement(5,HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_JADE_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_JADE_LARGE),
                rareOrePlacement(4,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(74)))
        );
        register(
                context,
                ORE_PYROPE,
                configuredFeature(context, ModConfiguredFeatures.ORE_PYROPE_SMALL),
                commonOrePlacement(5,HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(76)))
        );
        register(
                context, ORE_PYROPE_MEDIUM, configuredFeature(context, ModConfiguredFeatures.ORE_PYROPE_MEDIUM), commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)))
        );
        register(
                context,
                ORE_PYROPE_LARGE,
                configuredFeature(context, ModConfiguredFeatures.ORE_PYROPE_LARGE),
                rareOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20), VerticalAnchor.aboveBottom(72)))
        );
        register(
                context,
                ORE_ECLIPSE_GEM,
                configuredFeature(context, ModConfiguredFeatures.ORE_ECLIPSE_GEM),
                List.of(
                        CountPlacement.of(3),
                        RarityFilter.onAverageOnceEvery(8), // very rare in stone
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-20),
                                VerticalAnchor.absolute(40)
                        ),
                        BiomeFilter.biome()
                )
        );
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier frequencyModifier, PlacementModifier heightRange) {
        return List.of(frequencyModifier, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), heightRange);
    }
    
    private static ResourceKey<PlacedFeature> of(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, MoreOresModLoader.id(name));
    }

    private static Holder<ConfiguredFeature<?, ?>> configuredFeature(BootstrapContext<PlacedFeature> context, ResourceKey<ConfiguredFeature<?, ?>> key) {
        return context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(key);
    }
    
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
