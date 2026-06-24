package org.cobra.moreores.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;

import java.util.Map;
import java.util.Set;

public class AutomatedBlockLootCreator extends BlockLootSubProvider {
    private static final Map<Block, Item> ORE_DROPS = Map.ofEntries(
            Map.entry(ModBlocks.RUBY_ORE.get(), ModItems.RAW_RUBY.get()),
            Map.entry(ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModItems.RAW_RUBY.get()),
            Map.entry(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()),
            Map.entry(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()),
            Map.entry(ModBlocks.GREEN_SAPPHIRE_ORE.get(), ModItems.RAW_GREEN_SAPPHIRE.get()),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE.get(), ModItems.RAW_GREEN_SAPPHIRE.get()),
            Map.entry(ModBlocks.BLUE_GARNET_ORE.get(), ModItems.RAW_BLUE_GARNET.get()),
            Map.entry(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE.get(), ModItems.RAW_BLUE_GARNET.get()),
            Map.entry(ModBlocks.PINK_GARNET_ORE.get(), ModItems.RAW_PINK_GARNET.get()),
            Map.entry(ModBlocks.DEEPSLATE_PINK_GARNET_ORE.get(), ModItems.RAW_PINK_GARNET.get()),
            Map.entry(ModBlocks.GREEN_GARNET_ORE.get(), ModItems.RAW_GREEN_GARNET.get()),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE.get(), ModItems.RAW_GREEN_GARNET.get()),
            Map.entry(ModBlocks.KYAWTHUITE_ORE.get(), ModItems.RAW_KYAWTHUITE.get()),
            Map.entry(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE.get(), ModItems.RAW_KYAWTHUITE.get()),
            Map.entry(ModBlocks.TOPAZ_ORE.get(), ModItems.RAW_TOPAZ.get()),
            Map.entry(ModBlocks.DEEPSLATE_TOPAZ_ORE.get(), ModItems.RAW_TOPAZ.get()),
            Map.entry(ModBlocks.WHITE_TOPAZ_ORE.get(), ModItems.RAW_WHITE_TOPAZ.get()),
            Map.entry(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE.get(), ModItems.RAW_WHITE_TOPAZ.get()),
            Map.entry(ModBlocks.PERIDOT_ORE.get(), ModItems.RAW_PERIDOT.get()),
            Map.entry(ModBlocks.DEEPSLATE_PERIDOT_ORE.get(), ModItems.RAW_PERIDOT.get()),
            Map.entry(ModBlocks.JADE_ORE.get(), ModItems.RAW_JADE.get()),
            Map.entry(ModBlocks.DEEPSLATE_JADE_ORE.get(), ModItems.RAW_JADE.get()),
            Map.entry(ModBlocks.PYROPE_ORE.get(), ModItems.RAW_PYROPE.get()),
            Map.entry(ModBlocks.DEEPSLATE_PYROPE_ORE.get(), ModItems.RAW_PYROPE.get()),
            Map.entry(ModBlocks.ECLIPSE_GEM_ORE.get(), ModItems.CRYSTAL_OF_ECLIPSE.get())
    );
    
    public AutomatedBlockLootCreator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if(id.getNamespace().equals(MoreOresModLoader.MOD_ID)) {
                if(ORE_DROPS.containsKey(block)) {
                    for (Map.Entry<Block, Item> entrySet : ORE_DROPS.entrySet()) {
                        var block1 = entrySet.getKey();
                        var item = entrySet.getValue();
                        add(block1, createMultipleOreDrops(block1, item));
                    }
                }
                dropSelf(block);
            }
        }
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
