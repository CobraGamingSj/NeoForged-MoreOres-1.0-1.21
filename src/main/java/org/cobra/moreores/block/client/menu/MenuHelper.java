package org.cobra.moreores.block.client.menu;

import net.minecraft.world.entity.player.Inventory;
import org.cobra.moreores.block.entity.gem.AbstractGemMachineBlockEntity;

public interface MenuHelper<T extends AbstractGemMachineBlockEntity> {

    void addPlayerGenericInventory(Inventory playerInventory);

    void addPlayerHotbarInventory(Inventory playerInventory);

    T getBlockEntity();
}
