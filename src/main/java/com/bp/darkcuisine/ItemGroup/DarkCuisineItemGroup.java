package com.bp.darkcuisine.ItemGroup;

import com.bp.darkcuisine.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DarkCuisineItemGroup {
    public static final ItemGroup darkcuisine = FabricItemGroup.builder().icon(()->(new ItemStack(ModItems.Cooked_Mosquito_Cake))).
            displayName(Text.translatable(I18n.translate("DarkCuisine"))).
            entries((context,entries)->{
                entries.add(ModItems.Cooked_Mosquito_Cake);
                entries.add(ModItems.Mosquito_Cake);
                entries.add(ModItems.Mosquito_Corpse);
                entries.add(ModItems.Mosquito_Spawn_Egg);
                entries.add(ModItems.Frog_Juice1);
                entries.add(ModItems.Frog_Juice2);
                entries.add(ModItems.Frog_Foot);
            }).build();
    public static void initialize()
    {
        Registry.register(Registries.ITEM_GROUP, Identifier.of("com.bp.darkcuisine","darkcuisine"),darkcuisine);
    }

}
