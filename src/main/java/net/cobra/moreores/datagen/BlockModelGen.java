//package net.cobra.moreores.datagen;
//
//import net.cobra.moreores.MoreOresModLoader;
//import net.cobra.moreores.block.ModBlocks;
//import net.minecraft.data.PackOutput;
//import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
//import net.neoforged.neoforge.client.model.generators.ModelFile;
//import net.neoforged.neoforge.common.data.ExistingFileHelper;
//import net.neoforged.neoforge.registries.DeferredBlock;
//
//public class BlockModelGen extends BlockStateProvider {
//    public BlockModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
//        super(output, MoreOresModLoader.MOD_ID, existingFileHelper);
//    }
//
//    @Override
//    protected void registerStatesAndModels() {
//        simpleBlockWithItem(ModBlocks.GEM_POLISHER_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/gem_polisher")));
//    }
//
//    private void blockWithItem(DeferredBlock<?> deferredBlock) {
//        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
//    }
//
//    private void blockItem(DeferredBlock<?> deferredBlock) {
//        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("moreores:block/" + deferredBlock.getId().getPath()));
//    }
//
//    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
//        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("moreores:block/" + deferredBlock.getId().getPath() + appendix));
//    }
//}
