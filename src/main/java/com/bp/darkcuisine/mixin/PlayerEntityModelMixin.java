package com.bp.darkcuisine.mixin;

import com.bp.darkcuisine.entity.client.ObPlayerEntityModel;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.UnaryOperator;


@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin {
    @Inject(at = @At("HEAD"),
            method = "getTexturedModelData",
            cancellable = true)
    private static void getTexturedModelData(Dilation dilation, boolean slim, CallbackInfoReturnable<ModelData> cir){

        ModelData modelData = ObPlayerEntityModel.getModelData(dilation, 0.0F);

        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(
                EntityModelPartNames.HEAD,
                ModelPartBuilder.create().uv(0, 0).cuboid(-7.5F, -15F, -7.5F, 15F, 15F, 15F, dilation),
                ModelTransform.origin(0.0F, 0.0F, 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -8F, -2.0F, 15F, 12.0F, 15F, dilation),
                ModelTransform.origin(0.0F, 0.0F, 0.0F)
        );
        float f = 0.25F;
        if (slim) {
            ModelPartData modelPartData2 = modelPartData.addChild(
                    EntityModelPartNames.LEFT_ARM,
                    ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation),
                    ModelTransform.origin(5.0F, 2.0F, 0.0F)
            );
            ModelPartData modelPartData3 = modelPartData.addChild(
                    EntityModelPartNames.RIGHT_ARM,
                    ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation),
                    ModelTransform.origin(-5.0F, 2.0F, 0.0F)
            );
            modelPartData2.addChild(
                    "left_sleeve", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
            );
            modelPartData3.addChild(
                    "right_sleeve", ModelPartBuilder.create().uv(40, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
            );
        } else {
            ModelPartData modelPartData2 = modelPartData.addChild(
                    EntityModelPartNames.LEFT_ARM,
                    ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
                    ModelTransform.origin(5.0F, 2.0F, 0.0F)
            );
            ModelPartData modelPartData3 = modelPartData.getChild(EntityModelPartNames.RIGHT_ARM);
            modelPartData2.addChild(
                    "left_sleeve", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
            );
            modelPartData3.addChild(
                    "right_sleeve", ModelPartBuilder.create().uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
            );
        }

        ModelPartData modelPartData2 = modelPartData.addChild(
                EntityModelPartNames.LEFT_LEG,
                ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
                ModelTransform.origin(1.9F, 12.0F, 0.0F)
        );
        ModelPartData modelPartData3 = modelPartData.getChild(EntityModelPartNames.RIGHT_LEG);
        modelPartData2.addChild(
                "left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
        );
        modelPartData3.addChild(
                "right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
        );
        ModelPartData modelPartData4 = modelPartData.getChild(EntityModelPartNames.BODY);

        modelPartData4.addChild(
                EntityModelPartNames.JACKET, ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
        );




        cir.setReturnValue(modelData);
    }
}
