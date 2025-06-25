package com.bp.darkcuisine.entity;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class MobEntities {
    public static final RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(DarkCuisine.MOD_ID, "tste"));
    public static final EntityType<tsteEntity> ttt = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DarkCuisine.MOD_ID, "tste"),
            EntityType.Builder.create(tsteEntity::new, SpawnGroup.CREATURE).dimensions(0.75f, 0.75f).build(registryKey));
    public static void reg(){
        DarkCuisine.LOGGER.info("Reggggg");
    }
}
