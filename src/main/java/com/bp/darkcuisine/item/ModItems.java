package com.bp.darkcuisine.item;
import com.bp.darkcuisine.DarkCuisine;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item tst = regItems("tst",new Item(new Item.Settings()));
    private static Item regItems(String id,Item item){
        //return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(DarkCuisine.MOD_ID,id)),item);
        return Registry.register(Registries.ITEM, Identifier.of(DarkCuisine.MOD_ID,id),item);
    }
    public static void regModItems(){
        DarkCuisine.LOGGER.info("regI");
    }
}
