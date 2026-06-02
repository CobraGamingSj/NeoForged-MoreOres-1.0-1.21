package net.moreores.block.client.menu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.moreores.MoreOres;

public class GemPurifierScreen extends AbstractContainerScreen<GemPurifierMenu> {
    public static final Identifier THIS_IS_GUI_TEXTURE_FOR_MY_BLOCK_XD = MoreOres.id("textures/gui/container/gem_purifier/gui.png");
    
    public GemPurifierScreen(GemPurifierMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 196;
        this.imageHeight = 207;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, THIS_IS_GUI_TEXTURE_FOR_MY_BLOCK_XD, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}
