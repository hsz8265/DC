package com.bp.darkcuisine.entity;

import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EntityTesting implements ModInitializer {

    /*
     * 使用“entitytesting:cube”作为ID注册我们的实体
     *
     * 这个实体注册在了 SpawnGroup#CREATURE 类别下，大多数的动物和友好或中立的生物都注册在这个类别下。
     * 它有一个 0.75 × 0.75（或12个像素宽，即一个方块的3/4）大小的碰撞体积。
     */
    public static final RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(DarkCuisine.MOD_ID, "tste"));
    public static final EntityType<tsteEntity> tststtttttt = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DarkCuisine.MOD_ID, "tste"),
            EntityType.Builder.create(tsteEntity::new, SpawnGroup.CREATURE).dimensions(0.75f, 0.75f).build(registryKey)
    );

    @Override
    public void onInitialize() {

    }
}
