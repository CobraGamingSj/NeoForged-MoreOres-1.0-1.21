package org.cobra.moreores.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import org.jspecify.annotations.Nullable;

public class GemPurifierBlock extends BaseEntityBlock {
    public static final MapCodec<GemPurifierBlock> CODEC = simpleCodec(GemPurifierBlock::new);
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16);
    
    public GemPurifierBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()) {
            return InteractionResult.CONSUME;
        }
        if(level.getBlockEntity(pos) instanceof GemPurifierBlockEntity be){
            ((ServerPlayer) player).openMenu(new SimpleMenuProvider(be, be.getDisplayName()), pos);
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GemPurifierBlockEntity(blockPos,  blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, ModBlockEntityType.GEM_PURIFIER.get(), ((level1, blockPos, blockState, be) -> 
                be.tick(level1, blockPos, blockState)));
    }
}
