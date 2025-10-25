package me.carson.terrariaTools.toolFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class Tool {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final int cooldown;
    protected final ArrayList<String> lore;
    private final NamespacedKey uncraftableKey;
    private final NamespacedKey unplaceableKey;


    public Tool(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, int cooldown, ArrayList<String> lore) {
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        uncraftableKey=new NamespacedKey(plugin, "uncraftable");
        unplaceableKey=new NamespacedKey(plugin, "unplaceable");
        this.cooldown = cooldown;
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack aglet = new ItemStack(baseMaterial);
        ItemMeta meta = aglet.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria", texture));
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(unplaceableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        aglet.setItemMeta(meta);
        return aglet;
    }

    public boolean isThisItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        String storedId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(storedId);
    }

    public abstract void rightActivate(Player player);

    public abstract void cooldownEffect(Player player);

}