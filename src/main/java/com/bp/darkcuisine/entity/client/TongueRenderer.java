package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.entity.custom.TongueEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class TongueRenderer extends EntityRenderer<TongueEntity, TongueRenderState> {

    public TongueRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public TongueRenderState createRenderState() {
        return null;
    }
}
