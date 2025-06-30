package com.bp.darkcuisine;

import com.bp.darkcuisine.entity.client.GrabPayload;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static com.bp.darkcuisine.DarkCuisine.GRAB_KEY;

public class KeyInputHandler {
	public static void onClientTick(MinecraftClient client) {
		while (GRAB_KEY.wasPressed()) {
			if (client.player != null) {
				ClientPlayNetworking.send(new GrabPayload());
			}
		}
	}
}
