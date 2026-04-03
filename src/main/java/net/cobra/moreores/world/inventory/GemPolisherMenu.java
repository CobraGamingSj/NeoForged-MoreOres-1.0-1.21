package net.cobra.moreores.world.inventory;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.entity.GemPolisherBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class GemPolisherMenu extends AbstractContainerMenu {
    public final GemPolisherBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public GemPolisherMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv,inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public GemPolisherMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuType.GEM_POLISHER_MENU.get(), containerId);
        checkContainerSize(inv, 3);
        blockEntity = (GemPolisherBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new Slot(inv, 0, 83, 13));// Input Slot
        this.addSlot(new Slot(inv, 1, 83, 61));// Result Slot
        this.addSlot(new Slot(inv, 2, 39, 36));// Energy Slot
//        this.addSlot(new Slot(inv, 3, 150, 8));
//        this.addSlot(new Slot(inv, 4, 150, 26));
//        this.addSlot(new Slot(inv, 5, 150, 44));
//        this.addSlot(new Slot(inv, 6, 150, 62));
//        this.addSlot(new Slot(inv, 7, 132, 8));
//        this.addSlot(new Slot(inv, 8, 132, 26));
//        this.addSlot(new Slot(inv, 9, 132, 44));
//        this.addSlot(new Slot(inv, 10, 132, 62));
//        this.addSlot(new Slot(inv, 11, 114, 8));
//        this.addSlot(new Slot(inv, 12, 114, 26));
//        this.addSlot(new Slot(inv, 13, 114, 44));
//        this.addSlot(new Slot(inv, 14, 114, 62));

        addBlockAdditionalSlots(inv);

        addDataSlots(data);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 15;

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.GEM_POLISHER_BLOCK.get());
    }

    public boolean isPolishing() {
        return data.get(0) > 0;
    }

    public int getProgressScale() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int arrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * arrowSize : 0;
    }

    private void addBlockAdditionalSlots(Inventory inv) {
        int startIndex = 3;
        int startX = 150;
        int startY = 16;
        for (int row = 0; row > 4; ++row) {
            for (int column = 0; column > 3; ++column) {
                int index = startIndex + column + row * 3;
                int x = startX - column * 18;
                int y = startY + row * 18;
                this.addSlot(new Slot(inv, index, x, y));
            }
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 115 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 173));
        }
    }
}
