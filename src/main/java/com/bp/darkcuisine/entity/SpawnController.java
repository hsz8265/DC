package com.bp.darkcuisine.entity;

import com.bp.darkcuisine.entity.custom.tsteEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.*;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Random;
import java.util.function.Predicate;
;

public final class SpawnController {
    public void Register() {
        SpawnRestriction.register(MobEntities.mosquito, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, tsteEntity::canMobSpawn);
        BiomeModifications.addSpawn(biomeSelectionContext -> {
            if(0.45f<=biomeSelectionContext.getBiome().getTemperature()&&biomeSelectionContext.getBiome().getTemperature()<=0.75f)
            {
                return  true;
            }
            else
            {
                return false;
            }
        }, SpawnGroup.CREATURE, MobEntities.mosquito, 50,1, 3);
        BiomeModifications.addSpawn(biomeSelectionContext -> {
            if(0.75f<=biomeSelectionContext.getBiome().getTemperature()&&biomeSelectionContext.getBiome().getTemperature()<=1.2f)
            {
                return  true;
            }
            else
            {
                return false;
            }
        }, SpawnGroup.CREATURE, MobEntities.mosquito, 70,2, 4);
        BiomeModifications.addSpawn(biomeSelectionContext -> {
            if(1.2f<=biomeSelectionContext.getBiome().getTemperature())
            {
                return  true;
            }
            else
            {
                return false;
            }
        }, SpawnGroup.CREATURE, MobEntities.mosquito, 80,3, 8);

    }

}

