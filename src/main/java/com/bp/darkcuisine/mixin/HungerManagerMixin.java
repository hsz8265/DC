package com.bp.darkcuisine.mixin;

import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Logger;

@Mixin(HungerManager.class)
public class HungerManagerMixin
{
    @Shadow private int foodLevel;

    @Unique
    public int extrafoodlevel;
    @Inject(at = @At("HEAD"),
            method = "addInternal")
    private void addInternal(int nutrition, float saturation, CallbackInfo ci)
    {
        if(this.foodLevel>=20)
        {
            extrafoodlevel+=nutrition;
            DarkCuisine.LOGGER.info("{}",this.foodLevel);

        }

    }
    @Inject(at = @At("HEAD"),
            method = "update")
    private void update(ServerPlayerEntity player, CallbackInfo ci)
    {
        if(foodLevel<=0&&extrafoodlevel>0)
        {
            foodLevel+=1;
            extrafoodlevel-=1;
        }
        if(foodLevel>30){
            player.addStatusEffect(new StatusEffectInstance(DarkCuisine.wei));
        }
        if(foodLevel>60){
            player.kill(player.getWorld());
        }
    }


}
