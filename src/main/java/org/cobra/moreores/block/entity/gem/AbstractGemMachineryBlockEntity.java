package org.cobra.moreores.block.entity.gem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.impl.IGemstone;

public abstract class AbstractGemMachineryBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler main;
    
    protected MachineStatus machineStatus = MachineStatus.STOPPED;
    protected EnergyActivity energyActivity = EnergyActivity.OFF;
    protected IGemstone gem = IGemstone.NONE;
    
    protected int initialProgressTicks = 0;
    
    public AbstractGemMachineryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.main = new ItemStacksResourceHandler(inventoryStackSize());
    }

    public final SimpleEnergyHandler energyHandler = new SimpleEnergyHandler(energyCapacity(), maxEnergyInsertable(), maxEnergyExtractable()) {
        @Override
        protected void onEnergyChanged(int previousAmount) {
            super.onEnergyChanged(previousAmount);
            getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    };
    
    protected abstract boolean hasRecipe();
    public abstract int inventoryStackSize();
    public abstract int energyCapacity();
    public abstract int maxEnergyInsertable();
    public abstract int maxEnergyExtractable();
    
    public int energyAmount() {
        return energyHandler.getAmountAsInt();
    }

    protected boolean hasEnergySource() {
        return !energyStack().isEmpty() && (energyStack().is(ModItems.ENERGY_INGOT) || energyStack().is(ModBlocks.ENERGY_BLOCK.asItem()));
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, main.copyToList());
        output.putInt("Progress", initialProgressTicks);
        energyHandler.serialize(output);
        output.store("PolishingState", MachineStatus.CODEC, machineStatus);
        output.store("EnergyState", EnergyActivity.CODEC, energyActivity);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, main.copyToList());
        initialProgressTicks = input.getIntOr("Progress", 0);
        energyHandler.deserialize(input);
        machineStatus = input.read("PolishingState", MachineStatus.CODEC).orElse(MachineStatus.STOPPED);
        energyActivity = input.read("EnergyState", EnergyActivity.CODEC).orElse(EnergyActivity.OFF);
    }
    
    protected void insertEnergy() {
        if(!hasEnergySource() || energyHandler.getAmountAsLong() >= 1_000_000) {
            energyActivity = EnergyActivity.OFF;
            return;
        }
        int amount = energyStack().is(ModItems.ENERGY_INGOT) ? 102 : 154;
        if(level.hasNeighborSignal(getBlockPos())) amount *= (int) 2.5;
        try(Transaction transaction = Transaction.openRoot()) {
            long inserted = energyHandler.insert(amount, transaction);
            transaction.commit();
            if(inserted > 0) energyActivity = EnergyActivity.INSERTING;
            else energyActivity = EnergyActivity.OFF;
        }
    }

    protected void extractEnergy() {
        int amount = level.hasNeighborSignal(getBlockPos()) ? 64 : 13;
        try(Transaction transaction = Transaction.openRoot()) {
            energyHandler.extract(amount, transaction);
            transaction.commit();
        }
        energyActivity = EnergyActivity.EXTRACTING;
    }
    
    public IGemstone gemstone() {
        return IGemstone.NONE;
    }

    protected void increaseProgressTicks() {
        if(this.level.hasNeighborSignal(getBlockPos())) {
            initialProgressTicks += (int) 2.5;
        } else {
            initialProgressTicks++;
        }
    }

    protected boolean hasEnoughEnergy() {
        return this.energyHandler.getAmountAsInt() >= 13;
    }
    
    protected void clearProgress() {
        initialProgressTicks = 0;
    }

    public ItemStack getStack(int slot) {
        return main.getResource(slot).toStack();
    }

    public ItemStack ingredientStack() {
        return getStack(0);
    }

    public ItemStack resultStack() {
        return getStack(1);
    }

    public ItemStack energyStack() {
        return getStack(2);
    }
}
