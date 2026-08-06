package com.gamerofpro.operationshadow.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "operationshadow", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerSpawnEvent {

    private static final String GIVEN_DOSSIER_KEY = "operationshadow_has_dossier";

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) {
            return;
        }

        CompoundTag playerData = player.getPersistentData();

        if (!playerData.getBoolean(GIVEN_DOSSIER_KEY)) {
            ItemStack writtenBook = createMissionDossier();

            if (!player.getInventory().add(writtenBook)) {
                player.drop(writtenBook, false);
            }

            playerData.putBoolean(GIVEN_DOSSIER_KEY, true);
        }
    }

    private static ItemStack createMissionDossier() {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag tag = book.getOrCreateTag();

        tag.putString("title", "Operation Shadow");
        tag.putString("author", "Operation Shadow Command");
        tag.putInt("generation", 0);

        ListTag pages = new ListTag();

        // Page 1
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
                "§l§4OPERATION SHADOW§r\n\n" +
                "MISSION BRIEF\n\n" +
                "Welcome, Soldier.\n\n" +
                "You have been deployed to the combat zone.\n\n" +
                "Your objective is to establish a forward position, secure the area, and prepare for upcoming operations."
        ))));

        // Page 2
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
                "§lPRIMARY OBJECTIVES§r\n\n" +
                "☐ Gather essential supplies.\n\n" +
                "☐ Find weapons and ammunition.\n\n" +
                "☐ Capture enemy positions.\n\n" +
                "☐ Raise your unit's flag.\n\n" +
                "☐ Eliminate hostile forces."
        ))));

        // Page 3
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
                "§lFIELD INTELLIGENCE§r\n\n" +
                "Control of territory is critical.\n\n" +
                "Captured locations provide strategic advantages.\n\n" +
                "Protect your teammates and hold your captured objectives against enemy attacks."
        ))));

        // Page 4
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
                "§lFINAL ORDERS§r\n\n" +
                "Victory belongs to the side that controls the battlefield.\n\n" +
                "Fight with honor.\n\n" +
                "Complete your mission.\n\n" +
                "§7— Operation Shadow Command"
        ))));

        tag.put("pages", pages);
        return book;
    }
}
