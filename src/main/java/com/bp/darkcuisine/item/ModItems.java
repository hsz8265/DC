package com.bp.darkcuisine.item;
import com.bp.darkcuisine.DarkCuisine;
import com.bp.darkcuisine.entity.EntityTesting;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    public static final Item Cooked_Mosquito_Cake = register("cooked_mosquito_cake",Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(13).saturationModifier(4.2f).build(),
            ConsumableComponents.food().consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.STRENGTH,3200,1),
                                    new StatusEffectInstance(StatusEffects.POISON,1600,0),
                                    new StatusEffectInstance(StatusEffects.NAUSEA,1600,0)

    )
                            )
                    ).build()
            ));
    public static final Item Mosquito_Cake = register("mosquito_cake",Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.6f).build(),
            ConsumableComponents.food().consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.POISON,3200,1),
                                    new StatusEffectInstance(StatusEffects.NAUSEA,3200,1)

                            )
                    )
            ).build()
    ));
    public static final Item Mosquito_Corpse = register("mosquito_corpse",Item::new,new Item.Settings());
    public static final Item Mosquito_Spawn_Egg = register("mosquito_spawn_egg",settings->new SpawnEggItem(EntityTesting.mosquito,settings),new Item.Settings());
    //public static final Item Mosquito_Spawn_Egg1 = register("mosquito_spawn_egg", new SpawnEggItem(EntityTesting.tststtttttt,new Item.Settings()),new Item.Settings());
    /*
    private static Item regItems(String id,Item item){
        //return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(DarkCuisine.MOD_ID,id)),item);
        return Registry.register(Registries.ITEM, Identifier.of(DarkCuisine.MOD_ID,id),item);
    }
    */



    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DarkCuisine.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    /*
    public static Item register(String id, Item.Settings settings) {
        return register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DarkCuisine.MOD_ID,id)),Item::new, settings);
    }

     */
    public static void regModItems(){

        DarkCuisine.LOGGER.info("regI");
    }
}
