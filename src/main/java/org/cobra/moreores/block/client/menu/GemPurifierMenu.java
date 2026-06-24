package org.cobra.moreores.block.client.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.registry.ModItemTags;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class GemPurifierMenu extends AbstractContainerMenu {

    public final GemPurifierBlockEntity blockEntity;
    private final Level level;
    private final ContainerData containerData;
    
    public GemPurifierMenu(int containerId, Inventory inventory, FriendlyByteBuf extra) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(extra.readBlockPos()), new ItemStacksResourceHandler(16), new SimpleContainerData(2));
    }

    public GemPurifierMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ItemStacksResourceHandler handler, ContainerData containerData) {
        super(ModMenuType.GEM_PURIFIER.get(), containerId);
        this.blockEntity = ((GemPurifierBlockEntity) blockEntity);
        this.level = inventory.player.level();
        this.containerData = containerData;

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 0, 79, 11) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItemTags.RAW_GEMSTONE) || stack.is(ModItemTags.RAW_GEMSTONE_BLOCKS) || stack.is(ModItems.RAW_RUBY.get());
            }
        }); // Input
        
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 1, 79, 61) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItemTags.GEMSTONE) || stack.is(ModItemTags.GEMSTONE_BLOCKS) || stack.is(ModItems.RUBY.get());
            }
        }); // Result
        
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 2, 40, 20) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.ENERGY_INGOT.get()) || stack.is(ModBlocks.ENERGY_BLOCK.get().asItem());
            }
        }); // Energy Input
        
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, 3, 12, 20) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.WATER_BUCKET);
            }
        }); // Water Source
        
        for (int i = 0; i < 8; ++i) {
            this.addSlot(new ResourceHandlerSlot(handler, handler::set, 4 + i, 26 + i * 18, 95));
        }
        for (int i = 0; i < 4; ++i) {
            this.addSlot(new ResourceHandlerSlot(handler, handler::set, 12 +  i, 179, 115 + i * 18));
        }
        
        addPlayerGenericInventory(inventory);
        addPlayerHotbarInventory(inventory);
        
        addDataSlots(containerData);
    }

    public boolean isPurifying() {
        return containerData.get(0) > 0;
    }

    public int progressGetter() {
        int progress = this.containerData.get(0); //Progress
        int maxProgress = this.containerData.get(1); //Max Progress
        int progressArrowSize = 27; //Height of progress arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize/ maxProgress : 0;
    }
    
    public void addPlayerGenericInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 115 + i * 18));
            }
        }
    }

    public void addPlayerHotbarInventory(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 173));
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

    public float getEnergyPercent() {
        SimpleEnergyHandler energyHandler = this.blockEntity.energyHandler;
        int energy = energyHandler.getAmountAsInt();
        int maxEnergy = energyHandler.getCapacityAsInt();
        if (maxEnergy == 0 || energy == 0)
            return 0.0F;

        return Mth.clamp((float) energy / (float) maxEnergy, 0.0F, 1.0F);
    }
}