package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.entity.MobEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class EntityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(MobEntities.mosquito,(ctx -> {return new MosquitoRenderer(ctx);}));
        //EntityRendererRegistry.register(EntityTesting.tststtttttt,Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(MosquitoRenderer.MODEL_CUBE_LAYER,mosquitoModel::getTexturedModelData);
    }
}
