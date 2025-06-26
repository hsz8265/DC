package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.entity.EntityTesting;
import com.bp.darkcuisine.entity.MobEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import com.bp.darkcuisine.entity.custom.tsteEntity;
@Environment(EnvType.CLIENT)
public class EntityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityTesting.tststtttttt,(ctx -> {return new Renderer(ctx);}));
        //EntityRendererRegistry.register(EntityTesting.tststtttttt,Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(Renderer.MODEL_CUBE_LAYER,tm::getTexturedModelData);
    }
}
