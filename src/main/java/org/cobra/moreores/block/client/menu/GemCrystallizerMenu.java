package org.cobra.moreores.block.client.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class GemCrystallizerMenu extends AbstractContainerMenu {
    public GemCrystallizerMenu(int containerId, Inventory inventory, FriendlyByteBuf extra) {
        super(ModMenuType.GEM_CRYSTALLIZER.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
