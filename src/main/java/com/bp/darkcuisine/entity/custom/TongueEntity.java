// TongueEntity.java
package com.bp.darkcuisine.entity.custom;

import com.bp.darkcuisine.entity.MobEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TongueEntity extends ProjectileEntity {
    private static final int MAX_LIFETIME = 20; // 1秒最大存在时间
    private int ticksInAir = 0;
    private LivingEntity target;

    public TongueEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public TongueEntity(World world, PlayerEntity owner) {
        super(MobEntities.TONGUE, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY(), owner.getZ());

        // 设置初始速度（玩家视线方向）
        Vec3d rotationVec = owner.getRotationVec(1.0F);
        float speed = 1.5F;
        this.setVelocity(rotationVec.x * speed, rotationVec.y * speed, rotationVec.z * speed);
    }

    /**
     * Initializes data tracker.
     *
     * @param builder
     * @apiNote Subclasses should override this and add to the builder any data
     * that needs to be tracked.
     */
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
        ticksInAir++;

        // 检查是否超时
        if (ticksInAir > MAX_LIFETIME) {
            this.discard();
            return;
        }

        // 更新位置
        Vec3d velocity = this.getVelocity();
        Vec3d newPos = this.getPos().add(velocity);
        this.setPosition(newPos.x, newPos.y, newPos.z);

        // 粒子效果
        if (this.getWorld().isClient) {
            for (int i = 0; i < 3; i++) {
                this.getWorld().addParticleClient(
                        ParticleTypes.SPIT,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        velocity.x * 0.1,
                        velocity.y * 0.1,
                        velocity.z * 0.1
                );
            }
        }

        // 碰撞检测
        EntityHitResult hitResult = ProjectileUtil.getEntityCollision(
                this.getWorld(),
                this,
                this.getPos(),
                newPos,
                this.getBoundingBox().stretch(velocity),
                entity -> entity != this.getOwner() &&
                        entity instanceof LivingEntity &&
                        entity.isAlive()
        );

        if (hitResult != null) {
            onEntityHit(hitResult);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        super.onEntityHit(hitResult);
        Entity entity = hitResult.getEntity();

        if (entity instanceof LivingEntity livingEntity) {
            this.target = livingEntity;
            pullTarget();
            this.discard(); // 碰撞后移除舌头
        }
    }

    private void pullTarget() {
        if (this.getOwner() instanceof PlayerEntity player && target != null) {
            // 计算拉取方向
            Vec3d pullDirection = player.getPos()
                    .subtract(target.getPos())
                    .normalize();

            // 计算拉取力度
            double distance = player.distanceTo(target);
            double strength = 1.8 * (1.0 - Math.min(distance / 10.0, 0.8));

            // 应用速度
            target.setVelocity(pullDirection.multiply(strength));
            target.velocityModified = true;

            // 造成伤害
            target.damage((ServerWorld) target.getWorld(),target.getDamageSources().playerAttack(player), 4.0f);

            // 添加视觉效果
            spawnPullEffects();
        }
    }

    private void spawnPullEffects() {
        World world = this.getWorld();
        Vec3d pos = target.getPos();

        // 粒子效果
        for (int i = 0; i < 15; i++) {
            world.addParticleClient(
                    ParticleTypes.SCULK_SOUL,
                    pos.getX() + world.random.nextGaussian() * 0.5,
                    pos.getY() + world.random.nextDouble() * target.getHeight(),
                    pos.getZ() + world.random.nextGaussian() * 0.5,
                    0, 0.1, 0
            );
        }

        // 音效
        world.playSound(
                null,
                pos.x, pos.y, pos.z,
                SoundEvents.ENTITY_FROG_TONGUE,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f + world.random.nextFloat() * 0.2f
        );
    }

    protected void initDataTracker() {}

    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    protected void writeCustomDataToNbt(NbtCompound nbt) {}
    /*
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }*/
}
