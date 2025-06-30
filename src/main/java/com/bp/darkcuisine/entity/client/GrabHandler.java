package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.entity.custom.TongueEntity;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GrabHandler {
    private static final double DEFAULT_RANGE = 10.0;
    private static final float DEFAULT_DAMAGE = 4.0f;
    private static final float PULL_STRENGTH = 1.5f;
    private static final int COOLDOWN_TICKS = 20; // 1秒冷却

    public static void registerServerPacket() {
        // 注册服务端处理器

        ServerPlayNetworking.registerGlobalReceiver(
                GrabPayload.ID,
                (payload, context) -> {
                    ServerPlayerEntity player = context.player();
                    player.getServer().execute(() -> {
                        // 1. 检查冷却时间
                        //if (player.getItemCooldownManager().isCoolingDown(Items.DIAMOND)) {
                            //return; // 冷却中，不执行
                        //}

                        TongueEntity tongue = new TongueEntity(
                                player.getWorld(),
                                player
                        );
                        player.getWorld().spawnEntity(tongue);
                            // 7. 设置冷却时间
                            //player.getItemCooldownManager().set(Items.DIAMOND, COOLDOWN_TICKS);

                            // 8. 消耗饥饿值（可选）
                            if (!player.isCreative()) {
                                player.getWorld().playSound(
                                        null,
                                        player.getX(), player.getY(), player.getZ(),
                                        SoundEvents.ENTITY_FROG_EAT,
                                        SoundCategory.PLAYERS,
                                        0.7f,
                                        1.2f + player.getRandom().nextFloat() * 0.2f);
                                player.addExhaustion(2.0f);
                                // 消耗2点饥饿值
                            }
                        });
                    });
                }


    public static Entity getTargetedEntity(PlayerEntity player, double range) {
        Vec3d start = player.getEyePos();
        Vec3d direction = player.getRotationVec(1.0f);
        Vec3d end = start.add(direction.multiply(range));

        // 创建检测区域（适当扩展）
        Box searchBox = new Box(start, end).expand(1.5, 1.5, 1.5);

        // 使用高效的实体射线检测
        EntityHitResult hitResult = ProjectileUtil.raycast(
                player,
                start,
                end,
                searchBox,
                entity -> entity != player &&
                        entity instanceof LivingEntity &&
                        entity.isAlive() &&
                        entity.canHit() &&
                        !entity.isSpectator(),
                range * range
        );

        return hitResult != null ? hitResult.getEntity() : null;
    }

    public static void pullEntity(PlayerEntity player, LivingEntity target) {
        // 计算从目标到玩家的方向向量
        Vec3d pullDirection = player.getPos()
                .subtract(target.getPos())
                .normalize();

        // 计算拉取力度（考虑距离因素）
        double distance = player.distanceTo(target);
        double strength = 1.5 * (1.0 - Math.min(distance / 10.0, 0.8));

        // 应用速度
        target.setVelocity(pullDirection.multiply(strength));
        target.velocityModified = true;

        // 添加视觉效果
        spawnPullParticles(player.getWorld(), target.getPos());
        playPullSound(player.getWorld(), target.getPos());
    }

    private static void spawnPullParticles(World world, Vec3d pos) {
        Random random = world.getRandom();
        for (int i = 0; i < 8; i++) {
            world.addParticleClient(
                    ParticleTypes.ELECTRIC_SPARK,
                    pos.getX() + random.nextGaussian() * 0.3,
                    pos.getY() + random.nextDouble() * 1.2,
                    pos.getZ() + random.nextGaussian() * 0.3,
                    random.nextGaussian() * 0.02,
                    random.nextDouble() * 0.1,
                    random.nextGaussian() * 0.02
            );
        }
    }

    private static void playPullSound(World world, Vec3d pos) {
        world.playSound(
                null,
                pos.x, pos.y, pos.z,
                SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                SoundCategory.PLAYERS,
                0.7f,
                1.8f + world.random.nextFloat() * 0.2f
        );
    }
}
