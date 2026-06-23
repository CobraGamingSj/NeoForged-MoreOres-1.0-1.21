package org.cobra.moreores.block.client.menu;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GemCrystallizerScreen extends AbstractContainerScreen<GemCrystallizerMenu> {
    public GemCrystallizerScreen(GemCrystallizerMenu menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
        super(menu, inventory, title, 207, 197);
    }
}
