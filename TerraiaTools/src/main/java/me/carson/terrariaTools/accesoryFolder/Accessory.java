package me.carson.terrariaTools.accesoryFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class Accessory {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final ArrayList<String> lore;
    private final NamespacedKey activeKey;
    private final NamespacedKey uncraftableKey;
    private final NamespacedKey unplaceableKey;


    public Accessory(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, ArrayList<String> lore){
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        uncraftableKey=new NamespacedKey(plugin, "uncraftable");
        unplaceableKey=new NamespacedKey(plugin, "unplaceable");
        activeKey = new NamespacedKey(plugin,"activekey");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(baseMaterial);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(unplaceableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(activeKey, PersistentDataType.INTEGER, 0);
        meta.setMaxStackSize(Integer.valueOf(1));
        item.setItemMeta(meta);
        return item;
    }

    public boolean isThisItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        String storedId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(storedId);
    }

    public void setActivated(ItemStack item, boolean value) {
        if (item == null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        ArrayList<String> list= (ArrayList<String>) meta.getLore();
        if(value){
            data.set(activeKey, PersistentDataType.INTEGER, 1);
            list.set(list.size()-1,ChatColor.GRAY+"Shift Right Click to Deactivate");
        }else{
            data.set(activeKey, PersistentDataType.INTEGER, 0);
            list.set(list.size()-1,ChatColor.GRAY+"Shift Right Click to Activate");
        }
        meta.setLore(list);
        meta.setEnchantmentGlintOverride(value);
        item.setItemMeta(meta);
    }

    // Read the boolean value
    public boolean isActivated(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        return data.getOrDefault(activeKey, PersistentDataType.INTEGER, 0) == 1;
    }

    // Toggle the flag
    public void toggleActivated(ItemStack item) {
        setActivated(item, !isActivated(item));
    }

    public abstract void activateEffect(Player player);

    public abstract void deactivateEffect(Player player);
}
