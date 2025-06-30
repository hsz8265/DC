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

@Environment(EnvType.CLIENT)
public class EntityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(MobEntities.mosquito,(ctx -> {return new MosquitoRenderer(ctx);}));
        //EntityRendererRegistry.register(EntityTesting.tststtttttt,Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(MosquitoRenderer.MODEL_CUBE_LAYER,mosquitoModel::getTexturedModelData);
        ClientTickEvents.END_CLIENT_TICK.register(new ClientInputHandler());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyInputHandler.GRAB_KEY.wasPressed()) {
                if (client.player != null) {
                    // 发送抓取请求
                    ClientPlayNetworking.send(new GrabPayload());
                }
            }
        });

        // 2. 注册客户端Payload接收器
        ClientPlayNetworking.registerGlobalReceiver(
                DarkCuisine.PacketIdentifiers.GRAB_PACKET_ID,
                (payload, context) -> {
                    // 客户端不需要处理，但需要注册类型
                }
        );
    }
}
