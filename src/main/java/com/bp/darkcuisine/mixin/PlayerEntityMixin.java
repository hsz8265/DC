package com.bp.darkcuisine.mixin;


import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("HEAD"),
            method = "canConsume", cancellable = true)
    private void CanConsume(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir){
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.hasStatusEffect(DarkCuisine.wei)) {
            DarkCuisine.LOGGER.info("Can!!!!!!!!!!!");
            cir.setReturnValue(true);
        }
    }
}

