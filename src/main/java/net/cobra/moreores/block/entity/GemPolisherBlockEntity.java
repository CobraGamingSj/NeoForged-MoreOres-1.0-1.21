package net.cobra.moreores.block.entity;

import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.world.inventory.GemPolisherMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class GemPolisherBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static final int INGREDIENT_SLOT = 0;
    private static final int RESULT_SLOT = 1;
    private static final int ENERGY_SOURCE_SLOT = 2;

    private Lazy<IItemHandler> lazyItemHandler = Lazy.of();

    protected final ContainerData containerData;
    private int progress = 0;
    private int maxProgress = 400;

    public GemPolisherBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.GEM_POLISHER.get(), pos, blockState);
        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                  case 0 -> GemPolisherBlockEntity.this.progress ;
                  case 1 -> GemPolisherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemPolisherBlockEntity.this.progress = value;
                    case 1 -> GemPolisherBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = Lazy.of(() -> itemHandler);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.moreores.gem_polisher_block");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new GemPolisherMenu(containerId, inventory, this, this.containerData);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("gem_polisher.progress", progress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("gem_polisher.progress");

        super.loadAdditional(tag, registries);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (level.isClientSide) {
            return;
        }

        if (hasPolishingRecipe() && hasEnergySource()) {
            this.increaseGemPolishingProgress();
            if (isGemPolished()) {
                this.collectPolishedGem();
                this.resetProgressTicks();
            }
            setChanged(level, blockPos, blockState);
        } else {
            this.resetProgressTicks();
            setChanged(level, blockPos, blockState);
        }
    }

    private void increaseGemPolishingProgress() {
        progress++;
    }

    private void resetProgressTicks() {
        progress = 0;
    }

    private void collectPolishedGem() {
        ItemStack result = new ItemStack(ModItems.RUBY.get(), 1);
        this.itemHandler.extractItem(INGREDIENT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(RESULT_SLOT, new ItemStack(result.getItem(), this.itemHandler.getStackInSlot(RESULT_SLOT).getCount() + result.getCount()));
    }

    private boolean isGemPolished() {
        return progress >= maxProgress;
    }

    private boolean hasEnergySource() {
        return this.itemHandler.getStackInSlot(ENERGY_SOURCE_SLOT).is(ModItems.ENERGY_INGOT.get());
    }

    private boolean hasPolishingRecipe() {
        boolean hasRawGem = this.itemHandler.getStackInSlot(INGREDIENT_SLOT).getItem() == ModItems.RUBY.get();
        ItemStack result = new ItemStack(ModItems.RUBY.get());

        return hasRawGem && canInsertAmountIntoResultSlot(result.getCount()) && canInsertItemIntoResultSlot(result.getItem());
    }

    private boolean canInsertAmountIntoResultSlot(int count) {
        return this.itemHandler.getStackInSlot(RESULT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(RESULT_SLOT).getMaxStackSize();
    }

    private boolean canInsertItemIntoResultSlot(Item item) {
        return this.itemHandler.getStackInSlot(RESULT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(RESULT_SLOT).is(item);
    }
}
