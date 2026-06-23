package org.cobra.moreores.data;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.cuboid.ItemModelGenerator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.level.block.Block;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.GemPurifierBlock;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.equipment.ModEquipmentAssetKeys;
import org.cobra.moreores.item.util.impl.PurificationGemstones;

public class AutomaticModelCreator extends ModelProvider {
    public static final VariantMutator Y_ROT_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
    public static final VariantMutator Y_ROT_180 = VariantMutator.Y_ROT.withValue(Quadrant.R180);
    public static final VariantMutator Y_ROT_270 = VariantMutator.Y_ROT.withValue(Quadrant.R270);
    
    public AutomaticModelCreator(PackOutput output) {
        super(output, MoreOresModLoader.MOD_ID);
    }

    @Override
    public void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for(Item item : BuiltInRegistries.ITEM) {
            Identifier identifier = BuiltInRegistries.ITEM.getKey(item);
            if(identifier.getNamespace().equals(MoreOresModLoader.MOD_ID)) {
                ResourceKey<EquipmentAsset> assetKey = null;
                String path = identifier.getPath();
                boolean handheld = false;
                
                if(item instanceof BlockItem) {
                    continue;
                }

                if(path.endsWith("_sword") || path.endsWith("_shovel") ||
                        path.endsWith("_axe") || path.endsWith("_hoe") ||  path.endsWith("_pickaxe")) {
                    itemModels.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
                    handheld = true;
                } else if (path.endsWith("_spear")) {
                    itemModels.generateSpear(item);
                    handheld = true;
                } else if (path.startsWith("ruby_")) {
                    assetKey = ModEquipmentAssetKeys.RUBY;
                } else if (path.startsWith("sapphire_")) {
                    assetKey = ModEquipmentAssetKeys.SAPPHIRE;
                } else if (path.startsWith("radiant_")) {
                    assetKey = ModEquipmentAssetKeys.RADIANT;
                }

                if (assetKey != null) {
                    if(path.endsWith("_helmet")) {
                        itemModels.generateTrimmableItem(
                                item,
                                assetKey,
                                ItemModelGenerators.TRIM_PREFIX_HELMET,
                                false
                        );
                        continue;
                    } else if (path.endsWith("_chestplate")) {
                        itemModels.generateTrimmableItem(
                                item,
                                assetKey,
                                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
                                false
                        );
                        continue;
                    } else if (path.endsWith("_leggings")) {
                        itemModels.generateTrimmableItem(
                                item,
                                assetKey,
                                ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
                                false
                        );
                        continue;
                    } else if (path.endsWith("_boots")) {
                        itemModels.generateTrimmableItem(
                                item,
                                assetKey,
                                ItemModelGenerators.TRIM_PREFIX_BOOTS,
                                false
                        );
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

                                                String modelName = defineModelName(redstonePowered, gemstone);

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
                } else if(block == ModBlocks.GEM_CRYSTALLIZER_BLOCK.get()) {
//                    if(block == ModBlocks.GEM_PURIFIER_BLOCK.get()) {
//
//                        blockModels.blockStateOutput.accept(
//                                MultiVariantGenerator.dispatch(ModBlocks.GEM_CRYSTALLIZER_BLOCK.get())
//                                        .with(
//                                                PropertyDispatch.initial(
//                                                        GemPurifierBlock.FACING,
//                                                        GemPurifierBlock.REDSTONE_POWERED,
//                                                        GemPurifierBlock.IS_POLISHING
//                                                ).generate((direction, redstonePowered, gemstone) -> {
//
//                                                    String modelName = defineModelName(redstonePowered, gemstone);
//
//                                                    MultiVariant variant =
//                                                            BlockModelGenerators.plainVariant(
//                                                                    MoreOresModLoader.id("block/" + modelName)
//                                                            );
//
//                                                    return switch (direction) {
//                                                        case NORTH -> variant.with(Y_ROT_180);
//                                                        case EAST  -> variant.with(Y_ROT_270);
//                                                        case WEST  -> variant.with(Y_ROT_90);
//                                                        default    -> variant;
//                                                    };
//                                                })
//                                        )
//                        );
//
//                        continue;
//                    }
                    continue;
                }
                blockModels.createTrivialCube(block);
            }
        }
    }

    private static String defineModelName(boolean powered, PurificationGemstones gemstone) {
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
}