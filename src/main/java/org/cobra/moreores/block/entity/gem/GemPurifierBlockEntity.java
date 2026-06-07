package org.cobra.moreores.block.entity.gem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.client.menu.GemPurifierMenu;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.item.util.impl.IGem;
import org.cobra.moreores.item.util.impl.PurifyingGemstones;
import org.cobra.moreores.recipe.GemPurifierRecipe;
import org.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class GemPurifierBlockEntity extends AbstractGemBlockEntity {
    public final ItemStacksResourceHandler stack = new ItemStacksResourceHandler(16) {
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

    private long previousEnergyMilestone = 0;
    private long previousFluidMilestone = 0;
    private FluidState fluidState = FluidState.IDLE;
    
    private final ContainerData data;
    
    public static final int INGREDIENT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SLOT = 2;
    public static final int WATER_SLOT = 3;
    
    public final FluidStacksResourceHandler fluidHandler = new FluidStacksResourceHandler(1, 10_000);
    
//    private int progress = 0;
    private int maxProgress = 400;
    
    public GemPurifierBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.GEM_PURIFIER.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 1 -> GemPurifierBlockEntity.this.initialProgressTicks;
                    case 2 -> GemPurifierBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: GemPurifierBlockEntity.this.initialProgressTicks = value;
                    case 1: GemPurifierBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected boolean hasRecipe() {
        return false;
    }

    @Override
    public int mainStackSize() {
        return 16;
    }

    @Override
    public int getEnergyCapacity() {
        return 10_000_000;
    }

    @Override
    public int getMaxEnergyInsert() {
        return 192000;
    }

    @Override
    public int getMaxEnergyExtract() {
        return 512000;
    }

    @Override
    public ItemStack ingredientStack() {
        return getStack(INGREDIENT_SLOT);
    }

    @Override
    public ItemStack resultStack() {
        return getStack(RESULT_SLOT);
    }

    @Override
    public ItemStack energyStack() {
        return getStack(ENERGY_SLOT);
    }

    public ItemStack fluidStack() {
        return getStack(WATER_SLOT);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(stack.size());
        for (int i = 0; i < stack.size(); i++) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(stack, 0);
            inv.setItem(i, new ItemStack(itemAccess.getResource().getItem(), itemAccess.getAmount()));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }
    
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
        return new GemPurifierMenu(containerId, inventory, this, this.stack, data);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public FluidStack fluid() {
        return new FluidStack(fluidHandler.getResource(ENERGY_SLOT).getFluid(), fluidHandler.getAmountAsInt(ENERGY_SLOT));
    }
    
    public int fluidAmount() {
        return fluidHandler.getAmountFrom(fluid());
    }
    
    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide()) {
            return;
        }

        if(currentGemState == CurrentGemState.RUNNING) {
            energyState = EnergyState.EXTRACTING;
            if (isResultSlotAvailable() && hasRecipe() && hasEnoughEnergy() && hasEnoughWater()) {
                this.increaseProgressTicks();
                this.extractEnergy();
                this.consumeWater();
                if (hasPurificationFinished()) {
                    this.getPurifiedGemstone();
                    this.clearProgress();
                }
                setChanged(level, pos, state);
            } else {
                this.clearProgress();
                this.currentGemState = CurrentGemState.IDLE;
                setChanged(level, pos, state);
            }
        } else if (currentGemState.isPaused()) {
            energyState = EnergyState.INSERTING;
            fluidState = FluidState.FILLING;
            insertEnergy();
            fillWater();
        } else {
            if((energyAmount() < 10_000_000 && hasEnergySource()) || (fluidAmount() < 810000 && hasWaterBucket())) {
                energyState = EnergyState.INSERTING;
                insertEnergy();
                fluidState = FluidState.FILLING;
                fillWater();
            } else {
                energyState = EnergyState.IDLE;
                fluidState= FluidState.IDLE;
            }
        }

        checkForEnoughEnergyAndRemoveItem();
        checkForEnoughWaterAndRemoveBucket();
        setChanged(level, pos, state);
    }

    private Optional<RecipeHolder<GemPurifierRecipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(GemPurifierRecipe.Type.INSTANCE,
                        new GemPurifyingRecipeInput(stack.getResource(INGREDIENT_SLOT).toStack()), level);
    }
    
    private void getPurifiedGemstone() {
        Optional<RecipeHolder<GemPurifierRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().getResult();

        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(stack, RESULT_SLOT);

            stack.extract(stack.getResource(INGREDIENT_SLOT), 1, transaction);
            stack.set(RESULT_SLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());

            transaction.commit();
        }
    }

    private boolean hasPurificationFinished() {
        return initialProgressTicks >= maxProgress;
    }

    private void checkForEnoughEnergyAndRemoveItem() {
        int energy = energyAmount();

        long [] milestones = {1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 8000000, 10000000};

        for(long milestone : milestones) {
            if(energy >= milestone && previousEnergyMilestone < milestone) {
                stack.getResource(ENERGY_SLOT);
                ContainerHelper.removeItem(stack.copyToList(), ENERGY_SLOT, 1);
                previousEnergyMilestone = milestone;
                break;
            }
        }
    }

    private void checkForEnoughWaterAndRemoveBucket() {
        int water = fluidAmount();

        long [] milestones = {81000, 162000, 243000, 324000, 405000, 486000, 567000, 648000, 729000, 810000};

        for(long milestone : milestones) {
            if(water >= milestone && previousFluidMilestone < milestone) {
                ContainerHelper.removeItem(stack.copyToList(), WATER_SLOT, 1);
                stack.set(WATER_SLOT, ItemResource.of(new ItemStack(Items.BUCKET)), 1);
                previousFluidMilestone = milestone;
                break;
            }
        }
    }
    
    private boolean hasEnoughWater() {
        return fluid().getAmount() >= 1215;
    }

    protected boolean hasEnoughEnergy() {
        return this.energyHandler.getAmountAsInt() >= 128;
    }

    private boolean isResultSlotAvailable() {
        return this.resultStack().isEmpty() || this.resultStack().getCount() < this.resultStack().getMaxStackSize();
    }

    @Override
    protected void increaseProgressTicks() {
        if(this.level.hasNeighborSignal(getBlockPos())) {
            initialProgressTicks += 5;
        } else {
            initialProgressTicks++;
        }
    }

    private void fillWater() {
        if(!hasWaterBucket() || fluidAmount() >= 810000) {
            fluidState = FluidState.IDLE;
            return;
        }
        int amount = 1620;
        try(Transaction transaction = Transaction.openRoot()) {
            long inserted = fluidHandler.insert(fluidHandler.getResource(WATER_SLOT), amount / 81, transaction);
            transaction.commit();
            if(inserted > 0) fluidState = FluidState.FILLING;
            else fluidState = FluidState.IDLE;
        }
    }

    private void consumeWater() {
        int amount = 810;
        try(Transaction transaction = Transaction.openRoot()) {
            fluidHandler.extract(fluidHandler.getResource(WATER_SLOT), amount / 81, transaction);
            transaction.commit();
        }
        fluidState = FluidState.EMPTYING;
    }
    
    private boolean hasWaterBucket() {
        return !fluidStack().isEmpty() && fluidStack().is(Items.WATER_BUCKET);
    }
}
