package me.carson.terrariaTools.accesories;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


public class Aglet implements Listener {

    private final NamespacedKey key;
    private final NamespacedKey uncraftableKey;

    public Aglet(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "aglet");
        this.uncraftableKey = new NamespacedKey(plugin, "uncraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack createItem() {
        ItemStack aglet = new ItemStack(Material.COPPER_INGOT);
        ItemMeta meta = aglet.getItemMeta();
        meta.displayName(Component.text("Aglet", TextColor.fromHexString("#9696FF")));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","aglet"));
        meta.setMaxStackSize(Integer.valueOf(1));
        aglet.setItemMeta(meta);
        return aglet;
    }

    public void startAgletTask(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    boolean hasAglet = false;
                    for (ItemStack item : player.getInventory().getContents()) {
                        if (item != null && item.hasItemMeta() && "Aglet".equals(item.getItemMeta().getDisplayName())) {
                            hasAglet = true;
                            break;
                        }
                    }

                    if (hasAglet) {
                        // Give speed effect
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 0, true, false));
                        // Duration 40 ticks = 2 seconds, refreshed every tick
                    } else {
                        // Remove speed if they don't have it
                        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                            player.removePotionEffect(PotionEffectType.SPEED);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 40L); // Runs every two seconds
    }

}
