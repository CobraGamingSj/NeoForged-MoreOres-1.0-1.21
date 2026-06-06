package org.cobra.moreores.block.entity.gem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.cobra.moreores.item.util.impl.IGem;

public abstract class AbstractGemBlockEntity extends BlockEntity implements MenuProvider {

    protected IGem gem;
    
    public AbstractGemBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public IGem gemstone() {
        return IGem.NONE;
    }
}
