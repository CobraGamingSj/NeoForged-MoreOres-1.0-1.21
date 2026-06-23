package org.cobra.moreores.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemCrystallizerBlockEntity;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntityType {
    
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MoreOresModLoader.MOD_ID);
    
    public static final Supplier<BlockEntityType<GemPurifierBlockEntity>> GEM_PURIFIER = BLOCK_ENTITIES.register(
            "gem_purifier", () -> new BlockEntityType<>(GemPurifierBlockEntity::new, ModBlocks.GEM_PURIFIER_BLOCK.get())
    );
    public static final Supplier<BlockEntityType<GemCrystallizerBlockEntity>> GEM_CRYSTALLIZER = BLOCK_ENTITIES.register(
            "gem-crystallizer", () -> new BlockEntityType<>(GemCrystallizerBlockEntity::new, ModBlocks.GEM_CRYSTALLIZER_BLOCK.get())
    );
    
    public static void register(IEventBus schoolBus) {
        BLOCK_ENTITIES.register(schoolBus);
    }
}
