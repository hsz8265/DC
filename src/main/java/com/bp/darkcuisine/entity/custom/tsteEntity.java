package com.bp.darkcuisine.entity.custom;

import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class tsteEntity extends AnimalEntity {
    public tsteEntity(EntityType<? extends tsteEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this,20,true);
        this.lookControl = new LookControl(this);

    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH,5.0)
                .add(EntityAttributes.MOVEMENT_SPEED,1f)
                .add(EntityAttributes.FLYING_SPEED,0.5f)
                .build();
    }

    @Override
    public void tick(){
        super.tick();
        if(!this.isOnGround()){
            this.setMovementSpeed(0.3f);
        }

    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new BirdNavigation(this, world);
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(1,new MeleeAttackGoal(this,1.0,false));
        this.goalSelector.add(2,new FlyGoal(this,1));
    }
}
