package com.bp.darkcuisine;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
	public static final KeyBinding GRAB_KEY = KeyBindingHelper.registerKeyBinding(
			new KeyBinding("key.dark-cuisine.grab", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "category.dark-cuisine")
	);
}
