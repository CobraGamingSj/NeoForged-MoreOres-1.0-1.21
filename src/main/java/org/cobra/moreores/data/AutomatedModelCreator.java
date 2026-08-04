package org.cobra.moreores.data;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.GemCrystallizerBlock;
import org.cobra.moreores.block.GemPurifierBlock;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.RubyLampBlock;
import org.cobra.moreores.item.equipment.ModEquipmentAssets;
import org.cobra.moreores.item.util.impl.CrystallizationGemstones;
import org.cobra.moreores.item.util.impl.PurificationGemstones;

import java.util.List;
import java.util.Map;

public class AutomatedModelCreator extends ModelProvider {
    public static final VariantMutator Y_ROT_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
    public static final VariantMutator Y_ROT_180 = VariantMutator.Y_ROT.withValue(Quadrant.R180);
    public static final VariantMutator Y_ROT_270 = VariantMutator.Y_ROT.withValue(Quadrant.R270);
    
    public AutomatedModelCreator(PackOutput output) {
        super(output, MoreOresModLoader.MOD_ID);
    }

    @Override
    public void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        Map<String, Identifier> trimPrefixes = Map.of(
                "_helmet", ItemModelGenerators.TRIM_PREFIX_HELMET,
                "_chestplate", ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                "_leggings", ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                "_boots", ItemModelGenerators.TRIM_PREFIX_BOOTS
        );

        List<String> armorSuffixes = List.of("_chestplate", "_helmet", "_leggings", "_boots");

        Map<String, ResourceKey<EquipmentAsset>> vanillaAssets = Map.of(
                "iron_", EquipmentAssets.IRON,
                "gold_", EquipmentAssets.GOLD,
                "diamond_", EquipmentAssets.DIAMOND,
                "netherite_", EquipmentAssets.NETHERITE,
                "copper_", EquipmentAssets.COPPER,
                "leather_", EquipmentAssets.LEATHER,
                "chainmail_", EquipmentAssets.CHAINMAIL
        );

        Map<String, ResourceKey<EquipmentAsset>> modAssets = Map.of(
                "ruby_", ModEquipmentAssets.RUBY,
                "sapphire_", ModEquipmentAssets.SAPPHIRE,
                "radiant_", ModEquipmentAssets.RADIANT
        );
        
        for(Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);

            ResourceKey<EquipmentAsset> assetKey = null;
            String path = id.getPath();
            
            if(id.getNamespace().equals("minecraft")) {
                for(Map.Entry<String, ResourceKey<EquipmentAsset>> entry : vanillaAssets.entrySet()) {
                    for (String armorSuffix : armorSuffixes) {
                        if (path.startsWith(entry.getKey()) && path.endsWith(armorSuffix)) {
                            assetKey = entry.getValue();
                            break;
                        }
                    }
                }

                if (assetKey != null) {
                    boolean generated = false;
                    for (Map.Entry<String, Identifier> entry : trimPrefixes.entrySet()) {
                        String suffix = entry.getKey();
                        Identifier prefix = entry.getValue();
                        if(path.endsWith(suffix)) {
                            itemModels.generateTrimmableItem(item, assetKey, prefix, false);
                            generated = true;
                        }
                    }
                    if(generated) {
                        continue;
                    }
                }
                continue;
            }
            
            if(id.getNamespace().equals(MoreOresModLoader.MOD_ID)) {
                boolean handheld = false;
                
                if(item instanceof BlockItem) {
                    continue;
                }

                if(path.endsWith("_sword") || path.endsWith("_shovel") ||
                        path.endsWith("_axe") || path.endsWith("_hoe") ||
                        path.endsWith("_pickaxe")) {
                    itemModels.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
                    handheld = true;
                } else if (path.endsWith("_spear")) {
                    itemModels.generateSpear(item);
                    handheld = true;
                }

                for (Map.Entry<String, ResourceKey<EquipmentAsset>> entry : modAssets.entrySet()) {
                    for (String armorSuffix : armorSuffixes) {
                        if (path.startsWith(entry.getKey()) && path.endsWith(armorSuffix)) {
                            assetKey = entry.getValue();
                            break;
                        }
                    }
                }
                
                if(assetKey != null) {
                    boolean generated = false;
                    for (Map.Entry<String, Identifier> entry : trimPrefixes.entrySet()) {
                        String suffix = entry.getKey();
                        Identifier prefix = entry.getValue();
                        if(path.endsWith(suffix)) {
                            itemModels.generateTrimmableItem(item, assetKey, prefix, false);
                            generated = true;
                        }
                    }
                    if(generated) {
                        continue;
                    }
                }

                if(!handheld) itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
            }
        }

        for(Block block : BuiltInRegistries.BLOCK) {
            Identifier identifier = BuiltInRegistries.BLOCK.getKey(block);
            if(identifier.getNamespace().equals(MoreOresModLoader.MOD_ID)) {
                if(block == ModBlocks.GEM_PURIFIER_BLOCK.get()) {
                    
                    blockModels.blockStateOutput.accept(
                            MultiVariantGenerator.dispatch(ModBlocks.GEM_PURIFIER_BLOCK.get())
                                    .with(
                                            PropertyDispatch.initial(
                                                    GemPurifierBlock.FACING,
                                                    GemPurifierBlock.REDSTONE_POWERED,
                                                    GemPurifierBlock.IS_POLISHING
                                            ).generate((direction, redstonePowered, gemstone) -> {

                                                String modelName = defineModelNameForPurificationGemstones(redstonePowered, gemstone);

                                                MultiVariant variant =
                                                        BlockModelGenerators.plainVariant(
                                                                MoreOresModLoader.id("block/" + modelName)
                                                        );

                                                return switch (direction) {
                                                    case NORTH -> variant.with(Y_ROT_180);
                                                    case EAST  -> variant.with(Y_ROT_270);
                                                    case WEST  -> variant.with(Y_ROT_90);
                                                    default    -> variant;
                                                };
                                            })
                                    )
                    );
                    
                    continue;
                }

                if(block == ModBlocks.RUBY_LAMP.get()) {
                    Identifier lampOffIdentifier = TexturedModel.CUBE.create(ModBlocks.RUBY_LAMP.get(), blockModels.modelOutput);
                    Identifier lampOnIdentifier = blockModels.createSuffixedVariant(ModBlocks.RUBY_LAMP.get(), "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube);
                    blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.RUBY_LAMP.get())
                            .with(BlockModelGenerators.createBooleanModelDispatch(RubyLampBlock.LIT,
                                    new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOnIdentifier)).build()),
                                    new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOffIdentifier)).build()))));
                    continue;
                }
                
                if(block == ModBlocks.GEM_CRYSTALLIZER_BLOCK.get()) {

                    blockModels.blockStateOutput.accept(
                            MultiVariantGenerator.dispatch(ModBlocks.GEM_CRYSTALLIZER_BLOCK.get())
                                    .with(
                                            PropertyDispatch.initial(
                                                    GemCrystallizerBlock.FACING,
                                                    GemCrystallizerBlock.REDSTONE_POWERED,
                                                    GemCrystallizerBlock.IS_CRYSTALLIZING
                                            ).generate((direction, redstonePowered, gemstone) -> {
                                                String modelName = defineModelNameForCrystallizationGemstones(redstonePowered, gemstone);
                                                MultiVariant variant =
                                                        BlockModelGenerators.plainVariant(
                                                                MoreOresModLoader.id("block/" + modelName)
                                                        );
                                                return switch (direction) {
                                                    case NORTH -> variant.with(Y_ROT_180);
                                                    case EAST -> variant.with(Y_ROT_270);
                                                    case WEST -> variant.with(Y_ROT_90);
                                                    default -> variant;
                                                };
                                            })
                                    )
                    );
                    
                    continue;
                }
                blockModels.createTrivialCube(block);
            }
        }
    }

    private static String defineModelNameForPurificationGemstones(boolean powered, PurificationGemstones gemstone) {
        String prefix = powered
                ? "gem_purifier_block_rp"
                : "gem_purifier_block";

        return switch (gemstone) {
            case EMPTY -> prefix;
            case RUBY, PYROPE -> prefix + "_red";
            case SAPPHIRE, BLUE_GARNET -> prefix + "_blue";
            case GREEN_SAPPHIRE, GREEN_GARNET, PERIDOT, JADE -> prefix + "_green";
            case PINK_GARNET -> prefix + "_pink_garnet";
            case KYAWTHUITE, TOPAZ -> prefix + "_orange";
            case WHITE_TOPAZ -> prefix + "_white_topaz";
        };
    }

    private static String defineModelNameForCrystallizationGemstones(boolean powered, CrystallizationGemstones gemstone) {
        String prefix = powered
                ? "gem_crystallizer_block_rp"
                : "gem_crystallizer_block";

        return switch (gemstone) {
            case EMPTY -> prefix;
            case CRIMSON_GARNET -> prefix + "_crimson_garnet";
            case RADIANT_AMETHYST -> prefix + "_radiant_amethyst";
            case CRYSTALLITE -> prefix + "_crystallite";
            case ALEXANDRITE -> prefix + "_alexandrite";
            case LIMESTONE -> prefix + "_limestone";
            case MOONSTONE -> prefix + "_moonstone";
            case QUARTSIDIAN -> prefix + "_quartsidian";
            case ORANGE_ZIRCON -> prefix + "_orange_zircon";
            case OPAL -> prefix + "_opal";
            case GRANDIDIERITE -> prefix + "_c";
            case RED_BERYL -> prefix + "_red_beryl";
            case KASHMIR_SAPPHIRE -> prefix + "_kashmir_sapphire";
        };
    }
}