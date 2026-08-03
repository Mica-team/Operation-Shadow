package com.gamerofpro.operationshadow;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf me.Logger;

@Mod(OperationShadow.MOD_ID)
public class OperationShadow {

    // Unique Mod Identifier
    public static final String MOD_ID = "operationshadow";
    
    // Logger for console debugging
    public static final Logger LOGGER = LogUtils.getLogger();

    public OperationShadow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the common setup method for mod initialization
        modEventBus.addListener(this::commonSetup);

        // Register yourself for server and other game events we interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing Operation Shadow: Safe House & Mission System");
    }
}
