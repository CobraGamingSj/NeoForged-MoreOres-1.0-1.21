package net.moreores.block.client.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.moreores.block.ModBlocks;
import net.moreores.block.entity.gem.GemPurifierBlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class GemPurifierMenu extends AbstractContainerMenu {

    public final GemPurifierBlockEntity blockEntity;
    private final Level level;
    
    public GemPurifierMenu(int containerId, Inventory inventory, FriendlyByteBuf extra) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(extra.readBlockPos()), new ItemStacksResourceHandler(15));
    }

    public GemPurifierMenu(int containerId, Inventory inv, BlockEntity blockEntity, ItemStacksResourceHandler handler) {
        super(ModMenuType.GEM_PURIFIER.get(), containerId);
        this.blockEntity = ((GemPurifierBlockEntity) blockEntity);
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        addSlot(new ResourceHandlerSlot(handler, handler::set, 0, 80, 35) {
            @Override
            public int getMaxStackSize() {
                return 15;
            }
        });
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.GEM_PURIFIER_BLOCK.get());
    }
}
