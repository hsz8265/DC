package com.bp.darkcuisine.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

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

            Box b = new Box(entity.getPos(),entity.getPos()).expand(20);
            List<Entity> entities =world.getOtherEntities(entity, b);
            for (Entity e : entities) {
                if (e instanceof LivingEntity || e instanceof ItemEntity) {
                    // 计算拉取方向（从实体指向玩家）
                    Vec3d direction = entity.getPos().subtract(e.getPos()).normalize();
                    // 施加速度（模拟拉取效果）
                    e.setVelocity(direction.multiply(1.5)); // 调整数值控制拉取速度
                }
            }


            //((PlayerEntity) entity).kill(world);
            return super.applyUpdateEffect(world, entity, amplifier);
        }else{
            return false;
        }
    }
}
