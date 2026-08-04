package org.cobra.moreores.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.cobra.moreores.MoreOresModLoader;

import java.util.function.Consumer;

public class GemItem extends Item {
    public GemItem(Properties properties, String name) {
        super(properties.fireResistant().trimMaterial(ResourceKey.create(Registries.TRIM_MATERIAL, MoreOresModLoader.id(name))).rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.literal("Gemstone").withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}