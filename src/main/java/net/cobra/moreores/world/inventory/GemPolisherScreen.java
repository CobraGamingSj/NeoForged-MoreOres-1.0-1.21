//package net.cobra.moreores.world.inventory;
//
//import net.cobra.moreores.MoreOresModLoader;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Inventory;
//
//public class GemPolisherScreen extends AbstractContainerScreen<GemPolisherMenu> {
//    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MoreOresModLoader.MOD_ID, "textures/gui/container/gem_polisher/ gem_polisher_gui.png");
//
//    public GemPolisherScreen(GemPolisherMenu menu, Inventory playerInventory, Component title) {
//        super(menu, playerInventory, title);
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//        this.inventoryLabelY = 10000;
//        this.titleLabelY = 10000;
//    }
//
//    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
//        if (menu.isPolishing()) {
//            guiGraphics.blit(RenderType::guiTextured, TEXTURE, x + 88, y + 31, 176, 0, 8, menu.getProgressScale(), 256, 256);
//        }
//    }
//
//    @Override
//    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
//        int x = (width - imageWidth) / 2;
//        int y = (height - imageHeight) / 2;
//        guiGraphics.blit(RenderType::guiTextured, TEXTURE, i, i1, 0, 0, imageWidth, imageHeight, 256, 256);
//
//        renderProgressArrow(guiGraphics, i, i1);
//    }
//
//    @Override
//    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
//        super.render(guiGraphics, mouseX, mouseY, partialTick);
//        renderTooltip(guiGraphics, mouseX, mouseY);
//    }
//}
