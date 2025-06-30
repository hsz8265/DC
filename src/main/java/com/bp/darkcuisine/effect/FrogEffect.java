package com.bp.darkcuisine.effect;

import com.bp.darkcuisine.DarkCuisine;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.List;

import static net.minecraft.entity.projectile.ProjectileUtil.raycast;

public class FrogEffect extends StatusEffect {
    public FrogEffect() {
        super(StatusEffectCategory.BENEFICIAL,0xe9b8b3);


    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


    //@Override
    /*public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity && DarkCuisine.GRAB_KEY.wasPressed()){

            Entity target = getTargetedEntity(player, 10.0);
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
    }*/
}
