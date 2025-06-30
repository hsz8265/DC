package com.bp.darkcuisine.entity.client;

import com.bp.darkcuisine.KeyInputHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ClientInputHandler implements ClientTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftClient client) {
        while (KeyInputHandler.GRAB_KEY.wasPressed()) {
            if (client.player != null) {
                // 发送自定义 Payload
                ClientPlayNetworking.send(new GrabPayload());
            }
        }
    }
}
