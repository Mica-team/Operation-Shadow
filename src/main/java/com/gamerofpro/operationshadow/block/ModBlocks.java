package com.gamerofpro.operationshadow.block;

import com.gamerofpro.operationshadow.OperationShadow;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, OperationShadow.MOD_ID);

    public static final RegistryObject<Block> SKY_FLAG =
            BLOCKS.register("sky_flag",
                    () -> new Block(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLUE)
                            .strength(0.5F)
                            .noOcclusion()));

    public static final RegistryObject<Block> DESERT_FLAG =
            BLOCKS.register("desert_flag",
                    () -> new Block(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .strength(0.5F)
                            .noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
  }
