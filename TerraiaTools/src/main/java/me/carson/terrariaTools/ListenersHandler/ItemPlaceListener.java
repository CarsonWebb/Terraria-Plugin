package me.carson.terrariaTools.ListenersHandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemPlaceListener implements Listener {

    private final Plugin plugin;

    public ItemPlaceListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (item == null || !item.hasItemMeta()) return;
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        if (data.has(new org.bukkit.NamespacedKey(plugin, "unplaceable"), PersistentDataType.BYTE)) {
            event.setCancelled(true);
        }
    }
}
