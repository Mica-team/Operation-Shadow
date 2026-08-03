package com.gamerofpro.operationshadow;

import com.gamerofpro.operationshadow.block.ModBlocks;
import com.gamerofpro.operationshadow.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OperationShadow.MOD_ID)
public class OperationShadow {

    public static final String MOD_ID = "operationshadow";

    public OperationShadow() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        System.out.println("Operation Shadow loaded!");
    }
}
