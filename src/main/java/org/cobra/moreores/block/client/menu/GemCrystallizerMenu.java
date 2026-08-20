package org.cobra.moreores.block.client.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemCrystallizerBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.registry.ModItemTags;

public class GemCrystallizerMenu extends AbstractGemMachineMenu<GemCrystallizerBlockEntity> {
    private final Level level;
    private final ContainerData containerData;

    public GemCrystallizerMenu(int containerId, Inventory inventory, FriendlyByteBuf extra) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(extra.readBlockPos()), new ItemStacksResourceHandler(17), new SimpleContainerData(4));
    }

    public GemCrystallizerMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ItemStacksResourceHandler handler, ContainerData containerData) {
        super(ModMenuType.GEM_PURIFIER.get(), containerId, blockEntity.getBlockPos(), (GemCrystallizerBlockEntity) blockEntity);
        this.level = inventory.player.level();
        this.containerData = containerData;

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 0, 47, 22) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItemTags.RAW_GEMSTONE) || stack.is(ModItemTags.RAW_GEMSTONE_BLOCKS) || stack.is(ModItems.RAW_RUBY.get());
            }
        }); // Input Before

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 1, 87, 22) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItemTags.GEMSTONE) || stack.is(ModItemTags.GEMSTONE_BLOCKS) || stack.is(ModItems.RUBY.get());
            }
        }); // Input After

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 2, 67, 72) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.ENERGY_INGOT.get()) || stack.is(ModBlocks.ENERGY_BLOCK.get().asItem());
            }
        }); // Result Slot

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 3, 13, 21) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.ENERGY_INGOT.get()) || stack.is(ModBlocks.ENERGY_BLOCK.get().asItem());
            }
        }); // Energy Source

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 4, 39, 59) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.RADIANT_DUST.get());
            }
        }); // Radiant Dust Source

        for (int i = 0; i < 5; ++i) {
            this.addSlot(new ResourceHandlerSlot(handler, handler::set, 5 +  i, 179, 97 + i * 18));
        }

        addPlayerGenericInventory(inventory);
        addPlayerHotbarInventory(inventory);

        addDataSlots(containerData);
    }

    public int getRedstoneDust() {
        return this.containerData.get(3);
    }

    public boolean isCrystallizing() {
        return containerData.get(0) > 0;
    }

    public int getDustCount() {
        return containerData.get(2);
    }

    public int progressGetter() {
        int progress = this.containerData.get(0); //Progress
        int maxProgress = this.containerData.get(1); //Max Progress
        int progressArrowSize = 28; //Height of progress arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize/ maxProgress : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if(slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            stack = originalStack.copy();

            if(invSlot == 2) {
                if(!this.moveItemStackTo(originalStack, 18, 54, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(originalStack, stack);
            } else if(invSlot >= 18 && invSlot < 54) {
                if(isValidInput(originalStack)) {
                    if(!this.moveItemStackTo(originalStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isValidEnergyItem(originalStack)) {
                    if(!this.moveItemStackTo(originalStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isRadiantDust(originalStack)) {
                    if(!this.moveItemStackTo(originalStack, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else {
                    if(!this.moveItemStackTo(originalStack, 6, 18, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                if(!this.moveItemStackTo(originalStack, 18, 54, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if(originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }

    private boolean isValidInput(ItemStack stack) {
        return stack.is(ModItemTags.GEMSTONE_BLOCKS) || stack.is(ModItemTags.RAW_GEMSTONE_BLOCKS) ||
                stack.is(ModItemTags.RAW_GEMSTONE) || stack.is(ModItemTags.GEMSTONE);
    }

    private boolean isValidEnergyItem(ItemStack stack) {
        return stack.is(ModItems.ENERGY_INGOT) || stack.is(ModBlocks.ENERGY_BLOCK.asItem());
    }

    private boolean isRadiantDust(ItemStack stack) {
        return stack.is(ModItems.RADIANT_DUST);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.GEM_CRYSTALLIZER_BLOCK.get());
    }
}
