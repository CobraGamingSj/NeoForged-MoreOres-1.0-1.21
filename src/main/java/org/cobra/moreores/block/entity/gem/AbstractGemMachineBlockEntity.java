package org.cobra.moreores.block.entity.gem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.CrystallizationGemstones;
import org.cobra.moreores.item.util.impl.IGemstone;
import org.cobra.moreores.item.util.impl.PurificationGemstones;

public abstract class AbstractGemMachineBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler main;

    protected MachineStatus machineStatus = MachineStatus.STOPPED;
    protected MachineStatus.EnergyState energyState = MachineStatus.EnergyState.OFF;
    protected IGemstone gem = IGemstone.NONE;

    int energyExtracted = 0;

    protected int initialProgressTicks = 0;

    protected int redstone = 0;
    protected int maxRedstone = 10000;
    protected int redstoneTick;

    protected long previousRemovedEnergyMilestone = 0;
    protected long previousRemovedRedstoneMilestone = 0;

    public AbstractGemMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.main = new ItemStacksResourceHandler(inventoryStackSize());
    }

    final SimpleEnergyHandler energyHandler = new SimpleEnergyHandler(energyCapacity(), maxEnergyInsertable(), maxEnergyExtractable()) {
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

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, main.copyToList());
        output.putInt("Progress", initialProgressTicks);
        energyHandler.serialize(output);
        output.store("PolishingState", MachineStatus.CODEC, machineStatus);
        output.store("EnergyState", MachineStatus.EnergyState.CODEC, energyState);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, main.copyToList());
        initialProgressTicks = input.getIntOr("Progress", 0);
        energyHandler.deserialize(input);
        machineStatus = input.read("PolishingState", MachineStatus.CODEC).orElse(MachineStatus.STOPPED);
        energyState = input.read("EnergyState", MachineStatus.EnergyState.CODEC).orElse(MachineStatus.EnergyState.OFF);
    }

    public IGemstone detectGem(ItemStack stack) {
        Item item = stack.getItem();
        for (PurificationGemstones gems : PurificationGemstones.values()) {
            for (Item item1 : gems.items().get()) {
                if(item1 == item) {
                    return gems;
                }
            }
        }
        for (CrystallizationGemstones gems : CrystallizationGemstones.values()) {
            for(Item item1 : gems.items().get()) {
                if(item1 == item) {
                    return gems;
                }
            }
        }
        return IGemstone.NONE;
    }

    protected void giveEnergy() {
        if(!hasEnergySource() || energyHandler.getAmountAsInt() >= 1_000_000) {
            energyState = MachineStatus.EnergyState.OFF;
            return;
        }
        int amount = energyStack().is(ModItems.ENERGY_INGOT) ? 102 : 154;
        if(level.hasNeighborSignal(worldPosition)) amount *= (int) 2.5;
        try(Transaction transaction = Transaction.openRoot()) {
            long inserted = energyHandler().insert(amount, transaction);
            transaction.commit();
            if(inserted > 0) energyState = MachineStatus.EnergyState.INSERTING;
            else energyState = MachineStatus.EnergyState.OFF;
        }
    }

    protected void eatEnergy() {
        int amount = level.hasNeighborSignal(worldPosition) ? 64 : 13;
        try(Transaction transaction = Transaction.openRoot()) {
            long extracted = energyHandler().extract(amount, transaction);
            energyExtracted +=  extracted;
            transaction.commit();
        }
        energyState = MachineStatus.EnergyState.EXTRACTING;
    }

    protected void increaseProgressTicks() {
        if(this.level.hasNeighborSignal(getBlockPos())) {
            initialProgressTicks += (int) 2.5;
        } else {
            initialProgressTicks++;
        }
    }

    public int getRedstone() {
        return this.redstone;
    }

    public abstract GemCategory category();

    public IGemstone gemstone() {
        return detectGem(resultStack());
    }

    public void setGemstone(IGemstone gemstone) {
        this.gem = gemstone;
    }

    public SimpleEnergyHandler energyHandler() {
        return this.energyHandler;
    }

    public void setEnergyAmount(int energy) {
        this.energyHandler.set(Math.min(energy, energyCapacity()));
    }

    protected boolean hasEnergySource() {
        return this.energyStack().is(ModItems.ENERGY_INGOT) || this.energyStack().is(ModBlocks.ENERGY_BLOCK.asItem());
    }

    protected boolean hasRequiredEnergy() {
        return this.energyHandler.getAmountAsInt() >= 13;
    }

    protected void validateEnergyAmount() {
        if(energyAmount() > 10000000) {
            energyHandler.set(10000000);
        }

        long energy = this.energyAmount();

        long [] milestones = {1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 9000000, 10000000};

        for(long milestone : milestones) {
            if(energy == milestone && previousRemovedEnergyMilestone < milestone) {
                try(Transaction transaction = Transaction.openRoot()) {
                    this.main.extract(ItemResource.of(energyStack()), 1, transaction);
                    previousRemovedEnergyMilestone = milestone;
                    transaction.commit();
                    break;
                }
            }
        }
    }

    protected void validateRedstoneAmount(int slot) {
        if(redstone > 10000) {
            redstone = 10000;
        }

        int amount = redstone;

        int [] milestones = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

        for(long milestone : milestones) {
            if(amount == milestone && previousRemovedRedstoneMilestone < milestone) {
                try(Transaction transaction = Transaction.openRoot()) {
                    this.main.extract(ItemResource.of(redstoneStack(slot)), 1, transaction);
                    previousRemovedRedstoneMilestone = milestone;
                    transaction.commit();
                    break;
                }
            }
        }
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

    public ItemStack redstoneStack(int slot) {
        return getStack(slot);
    }
}