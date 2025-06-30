package com.bp.darkcuisine.entity;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.custom.TongueEntity;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.bp.darkcuisine.DarkCuisine.MOD_ID;

public class MobEntities {
    public static final RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, "mosquito"));
    public static final RegistryKey<EntityType<?>> registryKey2 = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, "tongue"));
    public static final EntityType<TongueEntity> TONGUE = EntityType.Builder.<TongueEntity>create(
                    (type, world) -> new TongueEntity(type, world), SpawnGroup.MISC)
            .dimensions(0.25F, 0.25F) // 小尺寸
            .maxTrackingRange(4)
            .trackingTickInterval(10)
            .build(registryKey2);

    public static void register() {
        Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(MOD_ID, "tongue"),
                TONGUE
        );
    }

        public static final EntityType<tsteEntity> mosquito = Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of(MOD_ID, "mosquito"),
                EntityType.Builder.create(tsteEntity::new, SpawnGroup.CREATURE).dimensions(0.4f, 0.4f).build(registryKey));
        public static void reg () {
            DarkCuisine.LOGGER.info("Reggggg");
        }
    }
