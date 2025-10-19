package me.carson.terrariaTools.tools;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class MomentumCapacitor implements Listener {

    private final NamespacedKey key;
    private final NamespacedKey uncraftableKey;

    public MomentumCapacitor(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "capacitor");
        this.uncraftableKey = new NamespacedKey(plugin, "uncraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Creates the custom clock item with an identifying tag.
     */
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.HEAVY_CORE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Momentum Capacitor", TextColor.fromHexString("#FF96FF")));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","momentum_capacitor"));
        meta.setMaxStackSize(Integer.valueOf(1));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getItem() == null) return;
        Player player = event.getPlayer();

        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (!data.has(key, PersistentDataType.BYTE)) return; // not the wind staff

        // Launch the player in the direction they're looking
        Vector dir = player.getLocation().getDirection();
        dir.setY(0);
        dir.normalize().multiply(0.4);
        player.setVelocity(player.getVelocity().add(dir));

        // Optional: Add sound or particles
        player.getWorld().playSound(player.getLocation(), "minecraft:entity.breeze.wind_burst", 1f, 1f);
        player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
    }

    private boolean isMomentumCapacitor(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        Byte value = meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE);
        return value != null && value == (byte) 1;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        // If it's your custom item, cancel placement
        if (isMomentumCapacitor(item)) {
            event.setCancelled(true);
        }
    }
}
