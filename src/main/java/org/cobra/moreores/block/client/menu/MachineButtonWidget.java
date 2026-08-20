package org.cobra.moreores.block.client.menu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.network.registration.ClientNetworkRegistry;

public class MachineButtonWidget extends Button {
    private final Identifier texture;
    private final int buttonIndex;
    private final BlockPos pos;

    public MachineButtonWidget(int x, int y, net.minecraft.network.chat.Component message, Identifier background, int buttonIndex, BlockPos pos) {
        super(x, y, 32, 32, message, btn -> {

        }, DEFAULT_NARRATION);
        this.texture = background;
        this.buttonIndex = buttonIndex;
        this.pos = pos;
    }

    @Override
    public void onPress(InputWithModifiers input) {
//        ClientNetworkRegistry.(new GemPurifierButtonClickPayload(buttonIndex, pos));
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        context.blit(RenderPipelines.GUI_TEXTURED, texture, getX(), getY(), 0, 0, this.getWidth(), this.getHeight(), 32, 32);
        context.outline(getX(), getY(), 32, 32, CommonColors.DARK_GRAY);
        if(isHovered()) {
            context.outline(getX(), getY(), 32, 32, CommonColors.BLACK);
        }
    }
}
