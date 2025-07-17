package com.bp.darkcuisine.mixin;

import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.ibm.icu.impl.duration.impl.DataRecord.EGender.F;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRenderMixin
{
    @Inject(at = @At("TAIL"),
            method = "scale(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;)V")
    protected void scale(PlayerEntityRenderState playerEntityRenderState, MatrixStack matrixStack, CallbackInfo ci) {
        ClientPlayerEntity CPE = MinecraftClient.getInstance().player;
        if(CPE.hasStatusEffect(DarkCuisine.dai)){
            matrixStack.scale(2.5F, 0.9375F, 2.5F);
        }
        //DarkCuisine.LOGGER.info("1");

    }
    
}
