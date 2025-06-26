package com.bp.darkcuisine;

import com.bp.darkcuisine.ItemGroup.DarkCuisineItemGroup;
import com.bp.darkcuisine.entity.MobEntities;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import com.bp.darkcuisine.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bp.darkcuisine.entity.EntityTesting.tststtttttt;

public class DarkCuisine implements ModInitializer {
	public static final String MOD_ID = "dark-cuisine";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.regModItems();
		DarkCuisineItemGroup.initialize();
		LOGGER.info("Hello Fabric world!");
		FabricDefaultAttributeRegistry.register(tststtttttt, tsteEntity.createAttributes());
	}
}