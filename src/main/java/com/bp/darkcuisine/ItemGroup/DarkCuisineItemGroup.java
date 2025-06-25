package com.bp.darkcuisine.ItemGroup;

import com.bp.darkcuisine.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DarkCuisineItemGroup {
    public static final ItemGroup darkcuisine = FabricItemGroup.builder().icon(()->(new ItemStack(ModItems.Cooked_Mosquito_Cake))).
            displayName(Text.translatable(I18n.translate("DarkCuisine"))).
            entries((context,entries)->{
                entries.add(ModItems.Cooked_Mosquito_Cake);
            }).build();
    public static void initialize()
    {
        Registry.register(Registries.ITEM_GROUP, Identifier.of("com.bp.darkcuisine","darkcuisine"),darkcuisine);
    }

}
