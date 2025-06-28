package com.bp.darkcuisine.entity.custom;

import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class tsteEntity extends HostileEntity {
    public static final AnimationState flyAnimationState = new AnimationState();
    public static final AnimationState attackAnimationState=new AnimationState();
    public tsteEntity(EntityType<? extends tsteEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this,20,true);
        this.lookControl = new LookControl(this);

    }
    @Override
    public boolean handleFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
        return false;
    }
    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f,1);
    }

    public static DefaultAttributeContainer createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH,4.0)
                .add(EntityAttributes.MOVEMENT_SPEED,1f)
                .add(EntityAttributes.FLYING_SPEED,1f)
                .add(EntityAttributes.ATTACK_DAMAGE,0.5f)
                .add(EntityAttributes.FOLLOW_RANGE,10)
                .build();
    }

    @Override
    public void tick(){
        super.tick();
        if(getWorld().getClosestPlayer(this,30)!=null){
            getMoveControl().moveTo(getWorld().getClosestPlayer(this,30).getX(),getWorld().getClosestPlayer(this,30).getY(),getWorld().getClosestPlayer(this,30).getZ(),0.5f);
        }



        if(!this.isOnGround()){

            this.setMovementSpeed(0.3f);
            flyAnimationState.startIfNotRunning(this.age);
        }
        else
        {
            flyAnimationState.stop();
        }

    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new BirdNavigation(this, world);
    }
    public void Attack()
    {
        attackAnimationState.startIfNotRunning(this.age);
    }

    @Override
    protected void initGoals() {
        //this.setTarget(getWorld().getClosestPlayer(this,30));
        this.goalSelector.add(0,new AttackPlayerGoal(this,0.5,false));
        this.targetSelector.add(0, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(0,new RevengeGoal(this));
        DarkCuisine.LOGGER.info(this.getTarget()==null?"1":"2");
        //this.goalSelector.add(2,new MeleeAttackGoal(this,1.0,false));
        //this.goalSelector.add(2,new FlyGoal(this,1));
        //this.goalSelector.add(1,new RevengeGoal(this));

    }


    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(world, damageSource, causedByPlayer);
    }


    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvent.of(Identifier.of(DarkCuisine.MOD_ID,"mos"));
    }
}
