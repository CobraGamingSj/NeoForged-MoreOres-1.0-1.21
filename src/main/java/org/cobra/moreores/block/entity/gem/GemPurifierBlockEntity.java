package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
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
import org.cobra.moreores.MoreOresModLoader;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.client.menu.GemPurifierMenu;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.IGemstone;
import org.cobra.moreores.item.util.impl.PurificationGemstones;
import org.cobra.moreores.recipe.GemPurifierRecipe;
import org.cobra.moreores.recipe.ModRecipeType;
import org.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
import org.cobra.moreores.registry.ModItemTags;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class GemPurifierBlockEntity extends AbstractGemMachineBlockEntity {
    public final ItemStacksResourceHandler stack = new ItemStacksResourceHandler(16) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            GemPurifierBlockEntity.this.setChanged();
            if(level != null && !level.isClientSide()) {
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
    private FluidState fluidState = FluidState.NONE;
    
    protected final ContainerData containerData;
    
    public static final int INGREDIENT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SLOT = 2;
    public static final int WATER_SLOT = 3;
    public static final int REDSTONE_SLOT = 4;

    public final FluidStacksResourceHandler fluidHandler = new FluidStacksResourceHandler(4, 10_000);
    
    private int maxProgress = 400;
    
    public GemPurifierBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.GEM_PURIFIER.get(), pos, blockState);
        this.gem = PurificationGemstones.EMPTY;
        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 1 -> GemPurifierBlockEntity.this.initialProgressTicks;
                    case 2 -> GemPurifierBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
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
        Optional<RecipeHolder<GemPurifierRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            MoreOresModLoader.LOGGER.info("No recipe found: {}", recipe.get().id());
            return false;
        }

        ItemStack result = recipe.get().value().assemble(new GemPurifyingRecipeInput(stack.getResource(INGREDIENT_SLOT).toStack()));

        return canInsertCountIntoResultSlot(result) && canInsertItemIntoResultSlot(result.getItem()) && hasRequiredEnergy() && hasEnoughWater();
    }

    @Override
    public int inventoryStackSize() {
        return 16;
    }

    @Override
    public int energyCapacity() {
        return 10_000_000;
    }

    @Override
    public int maxEnergyInsertable() {
        return 192000;
    }

    @Override
    public int maxEnergyExtractable() {
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
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(stack, i);
            inv.setItem(i, new ItemStack(itemAccess.getResource().getItem(), itemAccess.getAmount()));
        }
        if(level == null) {
            return;
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }
    
    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.store("GemType", PurificationGemstones.CODEC, gemstone());
        output.store("FluidState", FluidState.CODEC, fluidState);
        fluidHandler.serialize(output);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        gem = input.read("GemType", PurificationGemstones.CODEC).orElse(PurificationGemstones.EMPTY);
        fluidHandler.deserialize(input);
        fluidState = input.read("FluidState", FluidState.CODEC).orElse(FluidState.NONE);
    }

    @Override
    public PurificationGemstones gemstone() {
        IGemstone gemstone = super.gemstone();
        if(gemstone instanceof PurificationGemstones c) {
            return c;
        }
        return PurificationGemstones.EMPTY;
    }
    
    @Override
    public Component getDisplayName() {
        return ModBlocks.GEM_PURIFIER_BLOCK.get().getName();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new GemPurifierMenu(containerId, inventory, this, this.stack, containerData);
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
        return new FluidStack(fluidHandler.getResource(WATER_SLOT).getFluid(), fluidHandler.getAmountAsInt(WATER_SLOT));
    }
    
    public int fluidAmount() {
        return fluidHandler.getAmountFrom(fluid());
    }
    
    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide()) {
            return;
        }

        if(machineStatus == MachineStatus.RUNNING) {
            energyState = MachineStatus.EnergyState.EXTRACTING;
            if (isResultSlotAvailable() && hasRecipe() && hasRequiredEnergy() && hasEnoughWater()) {
                this.increaseProgressTicks();
                this.eatEnergy();
                this.drinkWater();
                if (hasPurificationFinished()) {
                    this.getPurifiedGemstone();
                    this.clearProgress();
                }
                setChanged(level, pos, state);
            } else {
                this.clearProgress();
                this.machineStatus = MachineStatus.STOPPED;
                setChanged(level, pos, state);
            }
        } else if (machineStatus.isPaused()) {
            energyState = MachineStatus.EnergyState.INSERTING;
            fluidState = FluidState.FILLING;
            giveEnergy();
            fillWater();
        } else {
            if((energyAmount() < 10_000_000 && hasEnergySource()) || (fluidAmount() < 810000 && hasWaterBucket())) {
                energyState = MachineStatus.EnergyState.INSERTING;
                giveEnergy();
                fluidState = FluidState.FILLING;
                fillWater();
            } else {
                energyState = MachineStatus.EnergyState.OFF;
                fluidState = FluidState.NONE;
            }
        }

        validateEnergyAmount();
        checkForEnoughWaterAndRemoveBucket();
        setChanged(level, pos, state);
    }

    private Optional<RecipeHolder<GemPurifierRecipe>> getCurrentRecipe() {
        if(level == null) {
            return Optional.empty();
        }
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(ModRecipeType.GEM_PURIFIER.get(),
                        new GemPurifyingRecipeInput(stack.getResource(INGREDIENT_SLOT).toStack()), level);
    }
    
    private void getPurifiedGemstone() {
        Optional<RecipeHolder<GemPurifierRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return;
        }
        ItemStack output = recipe.get().value().result();

        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(stack, RESULT_SLOT);

            stack.extract(stack.getResourceFrom(ingredientStack()), 1, transaction);
            stack.set(RESULT_SLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());

            transaction.commit();
        }
    }

    private boolean hasPurificationFinished() {
        return initialProgressTicks >= maxProgress;
    }

    @Override
    public void validateEnergyAmount() {
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

    protected boolean hasRequiredEnergy() {
        return this.energyHandler.getAmountAsInt() >= 128;
    }

    private boolean isResultSlotAvailable() {
        return this.resultStack().isEmpty() || this.resultStack().getCount() < this.resultStack().getMaxStackSize();
    }

    @Override
    public void increaseProgressTicks() {
        if(level == null) {
            return;
        }
        if(this.level.hasNeighborSignal(getBlockPos())) {
            initialProgressTicks += 5;
        } else {
            initialProgressTicks++;
        }
    }

    @Override
    public GemCategory category() {
        return GemCategory.PURIFYING;
    }

    private void fillWater() {
        if(!hasWaterBucket() || fluidAmount() >= 810000) {
            fluidState = FluidState.NONE;
            return;
        }
        int amount = 1620;
        try(Transaction transaction = Transaction.openRoot()) {
            long inserted = fluidHandler.insert(fluidHandler.getResource(WATER_SLOT), amount / 81, transaction);
            transaction.commit();
            if(inserted > 0) fluidState = FluidState.FILLING;
            else fluidState = FluidState.NONE;
        }
    }

    private void drinkWater() {
        int amount = 810;
        try(Transaction transaction = Transaction.openRoot()) {
            fluidHandler.extract(fluidHandler.getResource(WATER_SLOT), amount / 81, transaction);
            transaction.commit();
        }
        fluidState = FluidState.EMPTYING;
    }

    private boolean canInsertItemIntoResultSlot(Item item) {
        return this.resultStack().getItem() == item || this.resultStack().isEmpty() || this.resultStack().is(ModItemTags.GEMSTONE)
                || this.resultStack().is(ModItemTags.RAW_GEMSTONE);
    }

    private boolean canInsertCountIntoResultSlot(ItemStack result) {
        return this.resultStack().getCount() + result.getCount() <= this.resultStack().getMaxStackSize();
    }
    
    private boolean hasWaterBucket() {
        return !fluidStack().isEmpty() && fluidStack().is(Items.WATER_BUCKET);
    }

    public enum FluidState implements StringRepresentable {
        NONE("off"),
        FILLING("filling"),
        EMPTYING("emptying");

        private final String name;

        FluidState(String name) {
            this.name = name;
        }

        public static final Codec<FluidState> CODEC = StringRepresentable.fromValues(FluidState::values);

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}