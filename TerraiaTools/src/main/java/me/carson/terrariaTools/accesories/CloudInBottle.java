package me.carson.terrariaTools.accesories;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudInBottle implements Listener {

    private final NamespacedKey key;

    public CloudInBottle(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "cloud");
        // Register event listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack createItem() {
        ItemStack cloud = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta meta = cloud.getItemMeta();
        meta.displayName(Component.text("Cloud in a Bottle", TextColor.fromHexString("#9696FF")));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","cosmolight"));
        cloud.setItemMeta(meta);
        return cloud;
    }
}
