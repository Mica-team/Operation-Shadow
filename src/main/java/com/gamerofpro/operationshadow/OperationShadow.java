package com.gamerofpro.operationshadow;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(OperationShadow.MOD_ID)
public class OperationShadow {

    public static final String MOD_ID = "operationshadow";

    public OperationShadow() {
        MinecraftForge.EVENT_BUS.register(this);

        System.out.println("Operation Shadow loaded!");
    }
}
