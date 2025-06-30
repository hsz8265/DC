package com.bp.darkcuisine.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class FrogEffect extends StatusEffect {
    public FrogEffect() {
        super(StatusEffectCategory.BENEFICIAL,0xe9b8b3);


    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity){
            ((PlayerEntity) entity).kill(world);
            return super.applyUpdateEffect(world, entity, amplifier);
        }else{
            return false;
        }
    }
}
