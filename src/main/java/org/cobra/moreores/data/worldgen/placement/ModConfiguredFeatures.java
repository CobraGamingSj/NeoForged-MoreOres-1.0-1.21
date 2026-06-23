package org.cobra.moreores.data.worldgen.placement;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_SMALL = ModConfiguredFeatures.of("ore_ruby_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_MEDIUM = ModConfiguredFeatures.of("ore_ruby_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_LARGE = ModConfiguredFeatures.of("ore_ruby_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_SMALL = ModConfiguredFeatures.of("ore_sapphire_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_MEDIUM = ModConfiguredFeatures.of("ore_sapphire_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_LARGE = ModConfiguredFeatures.of("ore_sapphire_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GREEN_SAPPHIRE_SMALL = ModConfiguredFeatures.of("ore_green_sapphire_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GREEN_SAPPHIRE_MEDIUM = ModConfiguredFeatures.of("ore_green_sapphire_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GREEN_SAPPHIRE_LARGE = ModConfiguredFeatures.of("ore_green_sapphire_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BLUE_GARNET_SMALL = ModConfiguredFeatures.of("ore_blue_garnet_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BLUE_GARNET_MEDIUM = ModConfiguredFeatures.of("ore_blue_garnet_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BLUE_GARNET_LARGE = ModConfiguredFeatures.of("ore_blue_garnet_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PINK_GARNET_SMALL = ModConfiguredFeatures.of("ore_pink_garnet_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PINK_GARNET_MEDIUM = ModConfiguredFeatures.of("ore_pink_garnet_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PINK_GARNET_LARGE = ModConfiguredFeatures.of("ore_pink_garnet_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GREEN_GARNET_SMALL = ModConfiguredFeatures.of("ore_green_garnet_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GREEN_GARNET_MEDIUM = ModConfiguredFeatures.of("ore_green_garnet_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GREEN_GARNET_LARGE = ModConfiguredFeatures.of("ore_green_garnet_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KYAWTHUITE = ModConfiguredFeatures.of("ore_kyawthuite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOPAZ_SMALL = ModConfiguredFeatures.of("ore_topaz_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOPAZ_MEDIUM = ModConfiguredFeatures.of("ore_topaz_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TOPAZ_LARGE = ModConfiguredFeatures.of("ore_topaz_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_WHITE_TOPAZ_SMALL = ModConfiguredFeatures.of("ore_white_topaz_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_WHITE_TOPAZ_MEDIUM = ModConfiguredFeatures.of("ore_white_topaz_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_WHITE_TOPAZ_LARGE = ModConfiguredFeatures.of("ore_white_topaz_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PERIDOT_SMALL = ModConfiguredFeatures.of("ore_peridot_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PERIDOT_MEDIUM = ModConfiguredFeatures.of("ore_peridot_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PERIDOT_LARGE = ModConfiguredFeatures.of("ore_peridot_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_JADE_SMALL = ModConfiguredFeatures.of("ore_jade_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_JADE_MEDIUM = ModConfiguredFeatures.of("ore_jade_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_JADE_LARGE = ModConfiguredFeatures.of("ore_jade_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYROPE_SMALL = ModConfiguredFeatures.of("ore_pyrope_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYROPE_MEDIUM = ModConfiguredFeatures.of("ore_pyrope_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYROPE_LARGE = ModConfiguredFeatures.of("ore_pyrope_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ECLIPSE_GEM = ModConfiguredFeatures.of("ore_eclipse_gem");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest ruleTest2 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest ruleTest3 = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> rubyList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.RUBY_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> sapphireList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> greenSapphireList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.GREEN_SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> blueGarnetList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.BLUE_GARNET_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_BLUE_GARNET_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> pinkGarnetList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.PINK_GARNET_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_PINK_GARNET_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> greenGarnetList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.GREEN_GARNET_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_GREEN_GARNET_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> kyawthuiteList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.KYAWTHUITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_KYAWTHUITE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> topazList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.TOPAZ_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> whiteTopazList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.WHITE_TOPAZ_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> peridotList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.PERIDOT_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_PERIDOT_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> jadeList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.JADE_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> pyropeList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.PYROPE_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTest3, ModBlocks.DEEPSLATE_PYROPE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> eclipseGemList = List.of(
                OreConfiguration.target(ruleTest2, ModBlocks.ECLIPSE_GEM_ORE.get().defaultBlockState())
        );

        register(context, ORE_RUBY_SMALL, Feature.ORE, new OreConfiguration(rubyList, 1, 0.699F));
        register(context, ORE_RUBY_LARGE, Feature.ORE, new OreConfiguration(rubyList, 3, 0.69F));
        register(context, ORE_RUBY_MEDIUM, Feature.ORE, new OreConfiguration(rubyList, 3, 0.61F));
        register(context, ORE_SAPPHIRE_SMALL, Feature.ORE, new OreConfiguration(sapphireList, 1, 0.696F));
        register(context, ORE_SAPPHIRE_LARGE, Feature.ORE, new OreConfiguration(sapphireList, 4, 0.691F));
        register(context, ORE_SAPPHIRE_MEDIUM, Feature.ORE, new OreConfiguration(sapphireList, 4, 0.51F));
        register(context, ORE_GREEN_SAPPHIRE_SMALL, Feature.ORE, new OreConfiguration(greenSapphireList, 2, 0.616F));
        register(context, ORE_GREEN_SAPPHIRE_LARGE, Feature.ORE, new OreConfiguration(greenSapphireList, 4, 0.696F));
        register(context, ORE_GREEN_SAPPHIRE_MEDIUM, Feature.ORE, new OreConfiguration(greenSapphireList, 3, 0.57F));
        register(context, ORE_BLUE_GARNET_SMALL, Feature.ORE, new OreConfiguration(blueGarnetList, 3, 0.69F));
        register(context, ORE_BLUE_GARNET_LARGE, Feature.ORE, new OreConfiguration(blueGarnetList, 4, 0.699F));
        register(context, ORE_BLUE_GARNET_MEDIUM, Feature.ORE, new OreConfiguration(blueGarnetList, 3, 0.51F));
        register(context, ORE_PINK_GARNET_SMALL, Feature.ORE, new OreConfiguration(pinkGarnetList, 2, 0.52F));
        register(context, ORE_PINK_GARNET_LARGE, Feature.ORE, new OreConfiguration(pinkGarnetList, 4, 0.616F));
        register(context, ORE_PINK_GARNET_MEDIUM, Feature.ORE, new OreConfiguration(pinkGarnetList, 3, 0.52F));
        register(context, ORE_GREEN_GARNET_SMALL, Feature.ORE, new OreConfiguration(greenGarnetList, 2, 0.694F));
        register(context, ORE_GREEN_GARNET_LARGE, Feature.ORE, new OreConfiguration(greenGarnetList, 3, 0.695F));
        register(context, ORE_GREEN_GARNET_MEDIUM, Feature.ORE, new OreConfiguration(greenGarnetList, 4, 0.5F));
        register(context, ORE_KYAWTHUITE, Feature.ORE, new OreConfiguration(kyawthuiteList, 2, 0.0F));
        register(context, ORE_TOPAZ_SMALL, Feature.ORE, new OreConfiguration(topazList, 2, 0.69F));
        register(context, ORE_TOPAZ_LARGE, Feature.ORE, new OreConfiguration(topazList, 3, 0.695F));
        register(context, ORE_TOPAZ_MEDIUM, Feature.ORE, new OreConfiguration(topazList, 4, 0.5F));
        register(context, ORE_WHITE_TOPAZ_SMALL, Feature.ORE, new OreConfiguration(whiteTopazList, 2, 0.618F));
        register(context, ORE_WHITE_TOPAZ_LARGE, Feature.ORE, new OreConfiguration(whiteTopazList, 4, 0.697F));
        register(context, ORE_WHITE_TOPAZ_MEDIUM, Feature.ORE, new OreConfiguration(whiteTopazList, 3, 0.618F));
        register(context, ORE_PERIDOT_SMALL, Feature.ORE, new OreConfiguration(peridotList, 3, 0.57F));
        register(context, ORE_PERIDOT_LARGE, Feature.ORE, new OreConfiguration(peridotList, 3, 0.699F));
        register(context, ORE_PERIDOT_MEDIUM, Feature.ORE, new OreConfiguration(peridotList, 2, 0.697F));
        register(context, ORE_JADE_SMALL, Feature.ORE, new OreConfiguration(jadeList, 2, 0.5F));
        register(context, ORE_JADE_LARGE, Feature.ORE, new OreConfiguration(jadeList, 4, 0.699F));
        register(context, ORE_JADE_MEDIUM, Feature.ORE, new OreConfiguration(jadeList, 2, 0.615F));
        register(context, ORE_PYROPE_SMALL, Feature.ORE, new OreConfiguration(pyropeList, 2, 0.55F));
        register(context, ORE_PYROPE_LARGE, Feature.ORE, new OreConfiguration(pyropeList, 4, 0.697F));
        register(context, ORE_PYROPE_MEDIUM, Feature.ORE, new OreConfiguration(pyropeList, 3, 0.615F));
        register(context, ORE_ECLIPSE_GEM, Feature.ORE, new OreConfiguration(eclipseGemList, 3, 0.2F));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> of(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, MoreOresModLoader.id(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
