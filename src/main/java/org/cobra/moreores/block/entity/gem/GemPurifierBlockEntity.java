package org.cobra.moreores.block.entity.gem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.client.menu.GemPurifierMenu;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.item.util.impl.IGem;
import org.cobra.moreores.item.util.impl.PurifyingGemstones;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jspecify.annotations.Nullable;

public class GemPurifierBlockEntity extends AbstractGemBlockEntity {
    public final ItemStacksResourceHandler main = new ItemStacksResourceHandler(16) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            GemPurifierBlockEntity.this.setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }

        @Override
        protected int getCapacity(int index, ItemResource resource) {
            return 15;
        }
    };
    
    private final ContainerData data;
    
    public static final int INGREDIENT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SLOT = 2;
    public static final int WATER_SLOT = 3;
    
    private int progress = 0;
    private int maxProgress = 400;
    
    public GemPurifierBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.GEM_PURIFIER.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 1 -> GemPurifierBlockEntity.this.progress;
                    case 2 -> GemPurifierBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: GemPurifierBlockEntity.this.progress = value;
                    case 1: GemPurifierBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

//    public ItemStack getStack(int slot) {
//        return main.get(slot);
//    }
//
//    public ItemStack ingredientStack() {
//        return getStack(0);
//    }
//
//    public ItemStack resultStack() {
//        return getStack(1);
//    }
//
//    public ItemStack energyStack() {
//        return getStack(2);
//    }
//
//    public ItemStack fluidStack() {
//        return getStack(3);
//    }


    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putString("GemType", gem.getName());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.getStringOr("GemType", gemstone().name());
    }

    @Override
    public PurifyingGemstones gemstone() {
        IGem gem = super.gemstone();
        if(gem instanceof PurifyingGemstones c) {
            return c;
        }
        return PurifyingGemstones.NONE;
    }
    
    @Override
    public Component getDisplayName() {
        return ModBlocks.GEM_PURIFIER_BLOCK.get().getName();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new GemPurifierMenu(containerId, inventory, this, this.main, data);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide()) {
            return;
        }
    }
}
