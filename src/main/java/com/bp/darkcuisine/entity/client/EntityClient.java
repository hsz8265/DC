package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.KeyInputHandler;
import com.bp.darkcuisine.entity.MobEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

import static com.bp.darkcuisine.DarkCuisine.GRAB_KEY;

@Environment(EnvType.CLIENT)
public class EntityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(MobEntities.mosquito,(ctx -> {return new MosquitoRenderer(ctx);}));
        //EntityRendererRegistry.register(EntityTesting.tststtttttt,Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(MosquitoRenderer.MODEL_CUBE_LAYER,mosquitoModel::getTexturedModelData);


        EntityRendererRegistry.register(MobEntities.TONGUE,(ctx -> {return new TongueRenderer(ctx);}));

        // 注册按键处理器
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (GRAB_KEY.wasPressed()) {
                if (client.player != null) {
                    ClientPlayNetworking.send(new GrabPayload());
                }
            }
        });
    }
}
