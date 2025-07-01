package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.custom.TongueEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

import static com.bp.darkcuisine.entity.client.MosquitoRenderer.MODEL_CUBE_LAYER;
import static org.lwjgl.util.freetype.FT_LayerIterator.LAYER;

public class TongueRenderer extends EntityRenderer<TongueEntity, TongueRenderState> {

    private static final Identifier TEXTURE = Identifier.of(DarkCuisine.MOD_ID,"textures/entity/tongue/tg.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);

    //public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(Identifier.of(DarkCuisine.MOD_ID, "to"), "bone");
    //private final mosquitoModel model;

    public TongueRenderer(EntityRendererFactory.Context context) {

        super(context);
        //this.model = new mosquitoModel(context.getPart(MODEL_CUBE_LAYER));
    }

    @Override
    public TongueRenderState createRenderState() {
        return new TongueRenderState();
    }

    @Override
    public void render(TongueRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        //model.render(matrices,vertexConsumers,light,0);
        matrices.push();
        matrices.scale(2.0F, 2.0F, 2.0F);
        matrices.multiply(this.dispatcher.getRotation());
        MatrixStack.Entry entry = matrices.peek();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        produceVertex(vertexConsumer, entry, light, 0.0F, 0, 0, 1);
        produceVertex(vertexConsumer, entry, light, 1.0F, 0, 1, 1);
        produceVertex(vertexConsumer, entry, light, 1.0F, 1, 1, 0);
        produceVertex(vertexConsumer, entry, light, 0.0F, 1, 0, 0);
        matrices.pop();
        //.render(state, matrices, vertexConsumers, light);
        super.render(state, matrices, vertexConsumers, light);
    }


    private static void produceVertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, int light, float x, int z, int textureU, int textureV) {
        vertexConsumer.vertex(matrix, x - 0.5F,0.0F,  0.25F-z)
                .color(Colors.WHITE)
                .texture(textureU, textureV)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(matrix, 1.0F, 1.0F, 1.0F);
    }
}
