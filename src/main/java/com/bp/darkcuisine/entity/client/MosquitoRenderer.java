package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */
public class MosquitoRenderer extends MobEntityRenderer<tsteEntity, tstEntityRenderState,mosquitoModel> {

    private static final Identifier TEXTURE = Identifier.of(DarkCuisine.MOD_ID,"textures/item/white.png");
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(Identifier.of(DarkCuisine.MOD_ID, "mosquito"), "bone");
    public MosquitoRenderer(EntityRendererFactory.Context context) {
        super(context, new mosquitoModel(context.getPart(MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public tstEntityRenderState createRenderState() {
        return new tstEntityRenderState();
    }


    @Override
    public void updateRenderState(tsteEntity livingEntity, tstEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.flyAnimationState.copyFrom(tsteEntity.flyAnimationState);
        livingEntityRenderState.attackAnimationState.copyFrom(tsteEntity.attackAnimationState);
    }

    @Override
    public Identifier getTexture(tstEntityRenderState state) {
        return TEXTURE;
    }
}
