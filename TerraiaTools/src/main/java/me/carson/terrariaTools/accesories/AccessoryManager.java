package me.carson.terrariaTools.accesories;


import me.carson.terrariaTools.tools.MomentumCapacitor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AccessoryManager implements Listener {
    private final List<Accessory> items = new ArrayList<>();

    public AccessoryManager(Plugin plugin) {
        items.add(new Aglet(plugin));
        //items.add(new CloudInBottle(plugin));
    }

    public void startAccessoryTask(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (ItemStack item : player.getInventory().getContents()) {
                        if (item != null) {
                            for (Accessory itemx : items) {
                                if (itemx.isThisItem(item)) {
                                    itemx.activateEffect(player);
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 40L); // Runs every two seconds
    }
}