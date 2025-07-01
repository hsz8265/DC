// TongueEntity.java
package com.bp.darkcuisine.entity.custom;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.MobEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bp.darkcuisine.DarkCuisine.MOD_ID;
public class TongueEntity extends ProjectileEntity {
    private static final int MAX_LIFETIME = 20; // 0.4秒最大存在时间
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private int ticksInAir = 0;
    private LivingEntity target;
    public float damage =0f;
    public float speed=1f;

    public TongueEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public TongueEntity(World world, PlayerEntity owner,float speed,float damage) {
        super(MobEntities.TONGUE, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY()-0.15F, owner.getZ());

        this.speed=speed;
        this.damage=damage;
        // 设置初始速度（玩家视线方向）
        Vec3d rotationVec = owner.getRotationVec(1.0F);
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

        //DarkCuisine.LOGGER.info("RayCast");
        // 碰撞检测
        /*
        EntityHitResult hitResult = ProjectileUtil.getEntityCollision(

                this.getWorld(),
                this,
                this.getPos(),
                newPos,
                this.getBoundingBox().stretch(velocity).expand(5),
                (entity) -> {
                    if(entity != this.getOwner() &&
                            entity instanceof LivingEntity &&
                            entity.isAlive()){;return true;}
                    else {;return true;}
                }
        );

        if (hitResult != null) {
            DarkCuisine.LOGGER.info(hitResult.getEntity().getName().toString());
            super.onEntityHit(hitResult);
        }else{
            //DarkCuisine.LOGGER.info("NotHit");
        }

         */


        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        this.hitOrDeflect(hitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        //super.onEntityHit(hitResult);
        Entity entity = hitResult.getEntity();


        DarkCuisine.LOGGER.info("Hiiiiiiiiiiiiiiiiiiiiiiiiiiit");
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
            double strength = 0.4*distance;

            // 应用速度
            target.setVelocity(pullDirection.multiply(strength));
            target.velocityModified = true;

            // 造成伤害
            if (this.getWorld() instanceof ServerWorld serverWorld) {
                target.damage(serverWorld, target.getDamageSources().playerAttack(player), 2F+damage);
                if(target.isDead())
                {
                    target.getWorld().spawnEntity(new ItemEntity(target.getWorld(),target.getX(),target.getY(),target.getZ(),Items.PEARLESCENT_FROGLIGHT.getDefaultStack()));
                    target.getWorld().spawnEntity(new ItemEntity(target.getWorld(),target.getX(),target.getY(),target.getZ(),Items.VERDANT_FROGLIGHT.getDefaultStack()));
                    target.getWorld().spawnEntity(new ItemEntity(target.getWorld(),target.getX(),target.getY(),target.getZ(),Items.OCHRE_FROGLIGHT.getDefaultStack()));
                }
            }

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
                    ParticleTypes.SPIT,
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
