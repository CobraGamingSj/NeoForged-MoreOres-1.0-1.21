package net.cobra.moreores.block.entity;

import net.cobra.moreores.MoreOresModLoader;
import net.cobra.moreores.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MoreOresModLoader.MOD_ID);

    public static final Supplier<BlockEntityType<GemPolisherBlockEntity>> GEM_POLISHER = BLOCK_ENTITIES.register("gem_polisher", () -> new BlockEntityType<>(GemPolisherBlockEntity::new, ModBlocks.GEM_POLISHER_BLOCK.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
