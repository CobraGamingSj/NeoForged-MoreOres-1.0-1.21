package net.moreores.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.moreores.MoreOres;
import net.moreores.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MoreOres.MOD_ID);

    public static final DeferredBlock<Block> RUBY_BLOCK = register("ruby_block", () -> new Block(BlockBehaviour.Properties.of()));

    private static<T extends Block>DeferredBlock<T> register(String id, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(id, block);
        register(id, toReturn);
        return toReturn;
    }

    private static<T extends Block> void register(String id, DeferredBlock<T> block) {
        ModItems.ITEMS.register(id, () -> new Item(new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
       BLOCKS.register(eventBus);
    }

}
