package com.bp.darkcuisine.entity.custom;

import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.GameMode;

public class AttackPlayerGoal extends MeleeAttackGoal {



    public AttackPlayerGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public boolean canStart() {
        //DarkCuisine.LOGGER.info("Check!");
        //DarkCuisine.LOGGER.info(this.mob.getTarget()==null?"1":"2");
        return (getServerWorld(mob).getClosestPlayer(mob,30)!=null)&&(this.mob.distanceTo(getServerWorld(mob).getClosestPlayer(mob,30))<=2)&&(getServerWorld(mob).getClosestPlayer(mob,30).getGameMode()== GameMode.SURVIVAL||getServerWorld(mob).getClosestPlayer(mob,30).getGameMode()== GameMode.ADVENTURE);
    }

    @Override
    protected void attack(LivingEntity target) {
        super.attack(getServerWorld(mob).getClosestPlayer(mob,30));
        ((tsteEntity)mob).Attack();
    }
}
