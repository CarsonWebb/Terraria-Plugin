package me.carson.terrariaTools.accesories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class AccessoryManager implements Listener {
    private final List<Accessory> accessoryItems = new ArrayList<>();

    public AccessoryManager(Plugin plugin) {
        accessoryItems.add(new Aglet(plugin));
        accessoryItems.add(new ObsidianSkull(plugin));
        accessoryItems.add(new RedBalloon(plugin));
        accessoryItems.add(new BandOfRegeneration(plugin));
        //items.add(new CloudInBottle(plugin));
    }

    public void startAccessoryTask(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (ItemStack item : player.getInventory().getContents()) {
                        if (item != null) {
                            for (Accessory itemx : accessoryItems) {
                                if (itemx.isThisItem(item)) {
                                    itemx.activateEffect(player);
                                }
                            }
                        }
                    }
                }
                }, 0L, 100L); // Runs every five seconds
    }
}