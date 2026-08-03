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

        // Check persistent data flag
        if (!playerData.getBoolean(GIVEN_DOSSIER_KEY)) {
            ItemStack writtenBook = createMissionDossier();

            // Give item or drop on ground if inventory is full
            if (!player.getInventory().add(writtenBook)) {
                player.drop(writtenBook, false);
            }

            // Flag player so they only receive the book on initial login
            playerData.putBoolean(GIVEN_DOSSIER_KEY, true);
        }
    }

    private static ItemStack createMissionDossier() {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag tag = book.getOrCreateTag();

        // Basic Metadata
        tag.putString("title", "Operation Shadow");
        tag.putString("author", "HQ");
        tag.putInt("generation", 0); // Mark as Original

        ListTag pages = new ListTag();

        // Page 1
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "§lOperation Shadow§r\n\n" +
            "Mission Dossier - File #001\n\n" +
            "Welcome, Agent.\n\n" +
            "You have arrived at the Safe House.\n\n" +
            "Before you leave, complete your preparation. The exit door will remain locked until all objectives are finished."
        ))));

        // Page 2
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "§lObjectives§r\n\n" +
            "☐ Read this dossier.\n\n" +
            "☐ Search every storage chest in the Safe House.\n\n" +
            "☐ Collect the required crafting materials.\n\n" +
            "☐ Find the Creative Ammo Box."
        ))));

        // Page 3
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "☐ Craft a Homemade Shotgun.\n\n" +
            "☐ Make sure you are fully prepared.\n\n" +
            "---\n\n" +
            "§lFinal Objective§r\n\n" +
            "When all preparation tasks are complete, the Safe House security system will unlock the main door."
        ))));

        // Page 4
        pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(
            "Proceed to your first mission.\n\n" +
            "Good luck, Agent."
        ))));

        tag.put("pages", pages);
        return book;
    }
          }
                      
