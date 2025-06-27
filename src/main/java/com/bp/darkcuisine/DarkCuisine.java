package com.bp.darkcuisine;

import com.bp.darkcuisine.ItemGroup.DarkCuisineItemGroup;
import com.bp.darkcuisine.entity.MobEntities;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import com.bp.darkcuisine.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.world.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bp.darkcuisine.entity.MobEntities.mosquito;

public class DarkCuisine implements ModInitializer {
	public static final String MOD_ID = "dark-cuisine";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public  final EntityType<tsteEntity> mosquitoCopy=MobEntities.mosquito;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.regModItems();
		DarkCuisineItemGroup.initialize();
		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(mosquito, tsteEntity.createAttributes());
		//???为什么这行代码会让下方mosquito总是null啊

			SpawnRestriction.register(mosquitoCopy, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, tsteEntity::canMobSpawn);

			BiomeModifications.addSpawn(biomeSelectionContext -> {
				if (0.45f <= biomeSelectionContext.getBiome().getTemperature() && biomeSelectionContext.getBiome().getTemperature() <= 0.75f) {
					return true;
				} else {
					return false;
				}
			}, SpawnGroup.CREATURE, mosquitoCopy, 15, 1, 2);
			BiomeModifications.addSpawn(biomeSelectionContext -> {
				if (0.75f <= biomeSelectionContext.getBiome().getTemperature() && biomeSelectionContext.getBiome().getTemperature() <= 1.2f) {
					return true;
				} else {
					return false;
				}
			}, SpawnGroup.CREATURE, mosquitoCopy, 25, 1, 3);
			BiomeModifications.addSpawn(biomeSelectionContext -> {
				if (1.2f <= biomeSelectionContext.getBiome().getTemperature()) {
					return true;
				} else {
					return false;
				}
			}, SpawnGroup.CREATURE, mosquitoCopy, 40, 2, 3);

	}
}