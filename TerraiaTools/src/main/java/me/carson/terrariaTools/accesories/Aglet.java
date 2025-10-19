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



}
