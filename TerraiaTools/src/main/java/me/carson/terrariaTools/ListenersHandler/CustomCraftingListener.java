package me.carson.terrariaTools.ListenersHandler;

import me.carson.terrariaTools.recipieManagers.WeaponRecipieManager;
import org.bukkit.ChatColor;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCraftingListener implements Listener {

    private final NamespacedKey customMaterialKey;
    private final NamespacedKey customCraftableKey;

    public CustomCraftingListener(JavaPlugin plugin) {
        this.customMaterialKey = new NamespacedKey(plugin, "customMaterial");
        this.customCraftableKey = new NamespacedKey(plugin, "customCraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack[] matrix = inv.getMatrix();

        for (ItemStack item : matrix) {
            ItemStack result=inv.getResult();
            if(result!=null&&item!=null) {
                if (!item.getItemMeta().getPersistentDataContainer().has(customMaterialKey) && result.getItemMeta().getPersistentDataContainer().has(customCraftableKey)) {
                    inv.setResult(null);
                }
                if(item.getItemMeta().getPersistentDataContainer().has(customMaterialKey) && !result.getItemMeta().getPersistentDataContainer().has(customCraftableKey)){
                    inv.setResult(null);
                }
            }
        }
    }

}
