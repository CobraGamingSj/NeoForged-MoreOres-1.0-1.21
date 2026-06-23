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
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.item.util.impl.CrystallizationGemstones;
import org.cobra.moreores.item.util.impl.IGemstone;
import org.cobra.moreores.item.util.impl.PurificationGemstones;
import org.jspecify.annotations.Nullable;

public class GemCrystallizerBlockEntity extends AbstractGemMachineryBlockEntity {
    public final ItemStacksResourceHandler stack = new ItemStacksResourceHandler(17) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            GemCrystallizerBlockEntity.this.setChanged();
            if(level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }

        @Override
        protected int getCapacity(int index, ItemResource resource) {
            return 16;
        }
    };

    private long previousEnergyMilestone = 0;

    protected final ContainerData containerData;
    private int maxProgressTicks = 300;

    public int dustParticleCount = 0;
    public int maxDust = 10000;
    private int dustTick;
    
    public static final int INGREDIENT_BEFORE_SLOT = 0;
    public static final int INGREDIENT_AFTER_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int ENERGY_SOURCE_SLOT = 3;
    public static final int RADIANT_DUST_SLOT = 4;
    
    public GemCrystallizerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.GEM_CRYSTALLIZER.get(), pos, blockState);
        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemCrystallizerBlockEntity.this.initialProgressTicks;
                    case 1 -> GemCrystallizerBlockEntity.this.maxProgressTicks;
                    case 2 ->  GemCrystallizerBlockEntity.this.dustParticleCount;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemCrystallizerBlockEntity.this.initialProgressTicks = value;
                    case 1 -> GemCrystallizerBlockEntity.this.maxProgressTicks = value;
                    case 2 -> GemCrystallizerBlockEntity.this.dustParticleCount = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void setDustCount(int dustCount) {
        this.dustParticleCount = dustCount;
    }

    @Override
    protected boolean hasRecipe() {
        return false;
    }

    @Override
    public int inventoryStackSize() {
        return 16;
    }

    @Override
    public int energyCapacity() {
        return 1000000;
    }

    @Override
    public int maxEnergyInsertable() {
        return 19200;
    }

    @Override
    public int maxEnergyExtractable() {
        return 64000;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("DustCount", dustParticleCount);
        output.putInt("DustTick", dustTick);
        output.store("GemType", CrystallizationGemstones.CODEC, gemstone());
    }

    @Override
    public void loadAdditional(ValueInput view) {
        super.loadAdditional(view);
        dustParticleCount = view.getIntOr("DustCount", 0);
        dustTick = view.getIntOr("DustTick", 0);
        gem = view.read("GemType", CrystallizationGemstones.CODEC).orElse(CrystallizationGemstones.EMPTY);
    }

    @Override
    public CrystallizationGemstones gemstone() {
        IGemstone gemstone = super.gemstone();
        if(gemstone instanceof CrystallizationGemstones c) {
            return c;
        }
        return CrystallizationGemstones.EMPTY;
    }

    @Override
    public Component getDisplayName() {
        return ModBlocks.GEM_CRYSTALLIZER_BLOCK.get().getName();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        
    }
}
