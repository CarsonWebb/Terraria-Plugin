package me.carson.terrariaTools.armourFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Armour {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final int modelId;
    protected final ArrayList<String> lore;
    private final NamespacedKey uncraftableKey;


    public Armour(Plugin plugin, String name, String rarity, Material baseMaterial, String texture, String id, int modelId, ArrayList<String> lore) {
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        uncraftableKey=new NamespacedKey(plugin, "uncraftable");
        this.modelId = modelId;
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack armour = new ItemStack(baseMaterial);
        ItemMeta meta = armour.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        meta.setUnbreakable(true);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria", texture));
        meta.setCustomModelData(modelId);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(1));
        armour.setItemMeta(meta);
        return armour;
    }

}
