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

        // Ensure this only runs on the logical server
        if (player.level().isClientSide()) {
            return;
        }

        CompoundTag playerData = player.getPersistentData();

        // Check if player has already received the mission dossier
        if (!playerData.getBoolean(GIVEN_DOSSIER_KEY)) {
            ItemStack writtenBook = createMissionDossier();

            // Place book in player inventory, or drop it on the ground if full
            if (!player.getInventory().add(writtenBook)) {
                player.drop(writtenBook, false);
            }

            // Save persistent tag to prevent duplicate spawns on future logins
            playerData.putBoolean(GIVEN_DOSSIER_KEY, true);
        }
    }

    private static ItemStack createMissionDossier() {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag tag = book.getOrCreateTag();

        // Metadata
        tag.putString("title", "Operation Shadow: Target High Command");
        tag.putString("author", "Handler");
        tag.putInt("generation", 0); // Original written book

        ListTag pages = new ListTag();

        // Page 1: Briefing
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "§l§4Operation Shadow§r\n\n" +
            "Mission Dossier - File #001\n\n" +
            "Welcome, Agent.\n\n" +
            "You have arrived at the Safe House. Your high-value target has been located.\n\n" +
            "§lTarget:§r Adolf Hitler\n\n" +
            "Before insertion, complete your setup. The exit door remains sealed until ready."
        ))));

        // Page 2: Preparation Objectives
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "§lObjectives§r\n\n" +
            "☐ Read this dossier.\n\n" +
            "☐ Search every storage chest in the Safe House.\n\n" +
            "☐ Collect required crafting materials.\n\n" +
            "☐ Find the Creative Ammo Box."
        ))));

        // Page 3: Mission Goal
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "☐ Craft a Homemade Shotgun.\n\n" +
            "☐ Make sure you are fully prepared.\n\n" +
            "---\n\n" +
            "§lFinal Objective§r\n\n" +
            "Infiltrate the enemy compound and eliminate the target."
        ))));

        // Page 4: Outro
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "Once prepared, security locks will release.\n\n" +
            "Leave no witnesses.\n\n" +
            "Good luck, Agent."
        ))));

        tag.put("pages", pages);
        return book;
    }
            }
