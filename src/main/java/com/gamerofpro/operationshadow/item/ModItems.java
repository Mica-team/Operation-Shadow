package com.gamerofpro.operationshadow.item;

import com.gamerofpro.operationshadow.OperationShadow;
import com.gamerofpro.operationshadow.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OperationShadow.MOD_ID);

    public static final RegistryObject<Item> SKY_FLAG =
            ITEMS.register("sky_flag",
                    () -> new BlockItem(ModBlocks.SKY_FLAG.get(),
                            new Item.Properties()));

    public static final RegistryObject<Item> DESERT_FLAG =
            ITEMS.register("desert_flag",
                    () -> new BlockItem(ModBlocks.DESERT_FLAG.get(),
                            new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
