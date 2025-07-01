package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.custom.TongueEntity;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
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
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

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
        matrices.push();

        // 设置管道的基本缩放
        matrices.scale(1.0F, 1.0F, 8.0F);  // 调整长度（Z轴），可改变Z轴的缩放

        matrices.multiply(this.dispatcher.getRotation());
        MatrixStack.Entry entry = matrices.peek();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);

        int numSlices = 16;  // 设置管道截面的分段数（更多的分段可以得到更平滑的管道）
        float radius = 0.3F; // 管道的半径

        // 生成管道的每一段
        for (int i = 0; i < numSlices; i++) {
            float angle1 = (float) (i * Math.PI * 2 / numSlices);  // 当前分段的角度
            float angle2 = (float) ((i + 1) * Math.PI * 2 / numSlices);  // 下一个分段的角度

            // 当前点的坐标
            float x1 = (float) (radius * Math.cos(angle1));
            float z1 = (float) (radius * Math.sin(angle1));

            // 下一个点的坐标
            float x2 = (float) (radius * Math.cos(angle2));
            float z2 = (float) (radius * Math.sin(angle2));

            // 绘制管道的两个三角形面片（组成管道的侧面）
            produceVertex(vertexConsumer, entry, light, x1, -0.5F, z1, 0.0F); // 当前底部
            produceVertex(vertexConsumer, entry, light, x2, -0.5F, z2, 0.0F); // 下一个底部
            produceVertex(vertexConsumer, entry, light, x2, 0.5F, z2, 1.0F);  // 下一个顶部

            produceVertex(vertexConsumer, entry, light, x1, -0.5F, z1, 0.0F); // 当前底部
            produceVertex(vertexConsumer, entry, light, x2, 0.5F, z2, 1.0F);  // 下一个顶部
            produceVertex(vertexConsumer, entry, light, x1, 0.5F, z1, 1.0F);  // 当前顶部
        }

        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }

    // 修改后的produceVertex方法，接受z和y轴的坐标
    private static void produceVertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, int light, float x, float y, float z, float texCoord) {
        vertexConsumer.vertex(matrix, x, y, z)  // 设置x, y, z坐标
                .color(Colors.WHITE)  // 设置颜色
                .texture(texCoord, 0.0F)  // 纹理坐标
                .overlay(OverlayTexture.DEFAULT_UV)  // 默认覆盖纹理
                .light(light)  // 光照
                .normal(matrix, 0.0F, 1.0F, 0.0F);  // 法线方向
    }



}
