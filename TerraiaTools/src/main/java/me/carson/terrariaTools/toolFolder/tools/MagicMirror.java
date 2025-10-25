package me.carson.terrariaTools.toolFolder.tools;

import me.carson.terrariaTools.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MagicMirror extends Tool {

    public MagicMirror(Plugin plugin){
        super(plugin,"Magic Mirror","#9696FF", Material.GLASS,"magic_mirror","MagicMirror",20,new ArrayList<>(List.of(
                ChatColor.GRAY+"Gaze in the mirror to return home")));
    }

    @Override
    public void rightActivate(Player player) {
        Location loc=player.getRespawnLocation();
        assert loc != null;
        player.setFallDistance(0);
        player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.playSound(player.getLocation(), "terraria:magic_mirror_use", 1.0F, 1.0F);
    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MagicMirror(plugin).createItem();
    }
}
