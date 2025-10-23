package me.carson.terrariaTools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ResourcePackHandler implements Listener {

    // Your direct HTTPS link to the pack
    private static final String PACK_URL = "https://github.com/CarsonWebb/Terraria_Textures/releases/download/V4/Terraria.Textures.zip";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Send the resource pack when the player joins
        //player.sendMessage(ChatColor.YELLOW + "Downloading custom textures...");
        player.setResourcePack(PACK_URL);
    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        Player player = event.getPlayer();
        PlayerResourcePackStatusEvent.Status status = event.getStatus();

        switch (status) {
            case DECLINED:
                //player.kickPlayer(ChatColor.RED + "You must accept the resource pack to play on this server!");
                break;

            case FAILED_DOWNLOAD:
                //player.kickPlayer(ChatColor.RED + "Resource pack failed to download. Please check your connection and try again.");
                break;

            case SUCCESSFULLY_LOADED:
                //player.sendMessage(ChatColor.GREEN + "Custom textures loaded successfully!");
                break;

            case ACCEPTED:
                //player.sendMessage(ChatColor.GRAY + "Resource pack accepted. Loading...");
                break;
        }
    }
}