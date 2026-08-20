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
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.cobra.moreores.block.GemCrystallizerBlock;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.CrystallizationGemstones;
import org.cobra.moreores.item.util.impl.IGemstone;
import org.cobra.moreores.recipe.GemCrystallizerRecipe;
import org.cobra.moreores.recipe.ModRecipeType;
import org.cobra.moreores.recipe.input.GemCrystallizerRecipeInput;
import org.cobra.moreores.registry.ModItemTags;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class GemCrystallizerBlockEntity extends AbstractGemMachineBlockEntity {
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
            return 17;
        }
    };

    private long previousRemovedRadiantDustMilestone = 0;

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
    public static final int REDSTONE_SLOT = 5;

    public GemCrystallizerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.GEM_CRYSTALLIZER.get(), pos, blockState);
        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemCrystallizerBlockEntity.this.initialProgressTicks;
                    case 1 -> GemCrystallizerBlockEntity.this.maxProgressTicks;
                    case 2 ->  GemCrystallizerBlockEntity.this.dustParticleCount;
                    case 3 ->  GemCrystallizerBlockEntity.this.redstone;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemCrystallizerBlockEntity.this.initialProgressTicks = value;
                    case 1 -> GemCrystallizerBlockEntity.this.maxProgressTicks = value;
                    case 2 -> GemCrystallizerBlockEntity.this.dustParticleCount = value;
                    case 3 -> GemCrystallizerBlockEntity.this.redstone = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public void setDustCount(int dustCount) {
        this.dustParticleCount = dustCount;
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
    public GemCategory category() {
        return GemCategory.CRYSTALLIZATION;
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

    public ItemStack radiantDustStack() {
        return getStack(RADIANT_DUST_SLOT);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    @Override
    public ItemStack ingredientStack() {
        return getStack(INGREDIENT_BEFORE_SLOT);
    }

    public ItemStack ingredientAfterStack() {
        return getStack(INGREDIENT_AFTER_SLOT);
    }

    @Override
    public ItemStack energyStack() {
        return getStack(ENERGY_SOURCE_SLOT);
    }

    @Override
    public ItemStack resultStack() {
        return getStack(RESULT_SLOT);
    }

    @Override
    public ItemStack redstoneStack(int slot) {
        return getStack(REDSTONE_SLOT);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            return;
        }

        dustTick++;
        redstoneTick++;

        IGemstone newGem = gemstone();

        if (newGem != this.gem) {
            setGemstone(newGem);

            level.sendBlockUpdated(pos, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            setChanged(level, pos, state);
        }

        ItemStack stack = radiantDustStack();
        if(stack.is(ModItems.RADIANT_DUST) && dustParticleCount <= maxDust) {
            dustParticleCount += 2000;
            setChanged(level, pos, state);
        }
        ItemStack stack1 = redstoneStack(REDSTONE_SLOT);
        if((stack1.is(Items.REDSTONE) || level.hasNeighborSignal(pos)) && redstone <= maxRedstone) {
            redstone += 10;
            setChanged(level, pos, state);
        }

        changeState();
        if(machineStatus == MachineStatus.RUNNING) {
            energyState = MachineStatus.EnergyState.EXTRACTING;
            setChanged(level, pos, state);
            if (isResultSlotAvailable() && checkRecipe() && hasRequiredEnergyAmount() && dustParticleCount >= 15) {
                this.increaseProgressTicks();
                if((!level.hasNeighborSignal(pos) || redstone > 0) && redstoneTick >= 20) {
                    redstone--;
                    redstoneTick = 0;
                }
                this.eatEnergy();
                if(dustParticleCount > 0 && dustTick >= 20) {
                    dustParticleCount--;
                    dustTick = 0;
                    setChanged(level, pos, state);
                }
                setChanged(level, pos, state);
                if (hasCrystallizationFinished()) {
                    this.getCrystallizedGem();
                    this.clearProgress();
                    setChanged(level, pos, state);
                }
                setChanged(level, pos, state);
            } else {
                this.clearProgress();
                this.machineStatus = MachineStatus.IDLE;
                setChanged(level, pos, state);
            }
        } else if (machineStatus.isPaused()) {
            energyState = MachineStatus.EnergyState.INSERTING;
            giveEnergy();
            setChanged(level, pos, state);
        } else {
            if((energyAmount() < 1_000_000 && hasEnergySource())) {
                energyState = MachineStatus.EnergyState.INSERTING;
                giveEnergy();
                setChanged(level, pos, state);
            } else {
                energyState = MachineStatus.EnergyState.IDLE;
                setChanged(level, pos, state);
            }
        }

        validateEnergyAmount(ENERGY_SOURCE_SLOT);
        validateRedstoneAmount(REDSTONE_SLOT);
        validateRadiantDust();
        setChanged(level, pos, state);
    }

    private void getCrystallizedGem() {
        RecipeHolder<GemCrystallizerRecipe> recipe = currentRecipe().orElseThrow();

        ContainerHelper.removeItem(stack.copyToList(), INGREDIENT_BEFORE_SLOT, 1);
        ContainerHelper.removeItem(stack.copyToList(), INGREDIENT_AFTER_SLOT, 1);

        stack.set(RESULT_SLOT, ItemResource.of(recipe.value().result()),
                this.resultStack().getCount() + recipe.value().result().getCount());
    }
    private boolean hasCrystallizationFinished() {
        return initialProgressTicks >= maxProgressTicks;
    }

    private void validateRadiantDust() {
        if(dustParticleCount > 10000) {
            dustParticleCount = 10000;
        }

        long energy = dustParticleCount;

        long [] milestones = {2000, 4000, 6000, 8000, 10000};

        for(long milestone : milestones) {
            if(energy == milestone && previousRemovedRadiantDustMilestone < milestone) {
                ContainerHelper.removeItem(stack.copyToList(), RADIANT_DUST_SLOT, 1);
                previousRemovedRadiantDustMilestone = milestone;
                break;
            }
        }
    }

    private void changeState() {
        BlockState state = getBlockState();

        state = state.setValue(GemCrystallizerBlock.IS_CRYSTALLIZING, gemstone());


        if(state != getBlockState()) {
            level.setBlock(worldPosition, state, Block.UPDATE_ALL);
        }
    }

    @Override
    protected boolean checkRecipe() {
        Optional<RecipeHolder<GemCrystallizerRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasRequiredEnergyAmount() && canInsertCountIntoResultSlot(recipe.get().value().result())
                && canInsertItemIntoResultSlot(recipe.get().value().result().getItem());
    }

    private Optional<RecipeHolder<GemCrystallizerRecipe>> currentRecipe() {
        if(level == null) {
            return Optional.empty();
        }
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(ModRecipeType.GEM_CRYSTALLIZER.get(),
                        new GemCrystallizerRecipeInput(stack.getResource(INGREDIENT_BEFORE_SLOT).toStack(),
                                stack.getResource(INGREDIENT_AFTER_SLOT).toStack()), level);
    }

    private boolean canInsertItemIntoResultSlot(Item item) {
        return this.resultStack().getItem() == item || this.resultStack().isEmpty() || this.resultStack().is(ModItemTags.GEMSTONE)
                || this.resultStack().is(ModItemTags.RAW_GEMSTONE);
    }

    private boolean canInsertCountIntoResultSlot(ItemStack result) {
        return this.resultStack().getCount() + result.getCount() <= this.resultStack().getMaxStackSize();
    }

    private boolean isResultSlotAvailable() {
        return this.resultStack().isEmpty() || this.resultStack().getCount() < this.resultStack().getMaxStackSize();
    }
}
