package com.bp.darkcuisine.item;
import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public class ModItems {
    public static final Item tst = register("tst",Item::new,new Item.Settings());
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
