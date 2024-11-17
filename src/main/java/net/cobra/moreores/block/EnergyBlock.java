package net.cobra.moreores.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyBlock extends Block {
    public EnergyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
       if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
           livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20, 2));
           livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20, 2));
           livingEntity.addEffect(new MobEffectInstance(MobEffects.HARM, 20, 2));
       }
        super.stepOn(level, pos, state, entity);
    }
}
