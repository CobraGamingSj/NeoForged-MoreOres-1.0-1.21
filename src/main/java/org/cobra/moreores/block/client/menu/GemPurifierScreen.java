package org.cobra.moreores.block.client.menu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.cobra.moreores.MoreOres;

public class GemPurifierScreen extends AbstractContainerScreen<GemPurifierMenu> {
    public static final Identifier THIS_IS_GUI_TEXTURE_FOR_MY_BLOCK_XD = MoreOres.id("textures/gui/container/gem_purifier/gem_purifier_gui.png");
    
    public GemPurifierScreen(GemPurifierMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 207, 196);
    }
    
    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float a) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, THIS_IS_GUI_TEXTURE_FOR_MY_BLOCK_XD, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        renderProgressArrow(guiGraphics, x, y);
        renderEnergyStorageHandler(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isPurifying()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, THIS_IS_GUI_TEXTURE_FOR_MY_BLOCK_XD, x + 83, y + 31, 207, 0,
                    10, menu.progressGetter(), BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
        }
    }

    private void renderEnergyStorageHandler(GuiGraphicsExtractor context, int x, int y) {
        int energyBarSize = Mth.ceil(this.menu.getEnergyPercent() * 44);
        int gradientStart = -16776961;
        int gradientEnd = -16711936;
        context.fillGradient(x + 40, y + 42 + 44 - energyBarSize, x + 40 + 16, y + 42 + 44, gradientStart, gradientEnd);
    }
}
