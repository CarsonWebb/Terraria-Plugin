package me.carson.terrariaTools.ListenersHandler;

import me.carson.terrariaTools.recipieManagers.WeaponRecipieManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftBlockerListener implements Listener {

    private final NamespacedKey customKey;

    public CraftBlockerListener(JavaPlugin plugin) {
        this.customKey = new NamespacedKey(plugin, "uncraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();

        for (ItemStack item : inv.getMatrix()) {
            if (item == null) continue;

            ItemMeta meta = item.getItemMeta();
            if (meta == null) continue;

            PersistentDataContainer data = meta.getPersistentDataContainer();

            if (data.has(customKey, PersistentDataType.BYTE)) {
                // Found one of our custom items in the recipe
                inv.setResult(null); // prevent crafting
                return;
            }
        }
    }
}

