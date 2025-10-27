package me.carson.terrariaTools.materialsFolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Material {

    protected final Plugin plugin;
    protected final String name;
    protected final String rarity;
    protected final org.bukkit.Material baseMaterial;
    protected final String texture;
    protected final String id;
    protected final ArrayList<String> lore;
    private final NamespacedKey customMaterialKey;


    public Material(Plugin plugin, String name, String rarity, org.bukkit.Material baseMaterial, String texture, String id, ArrayList<String> lore){
        this.plugin = plugin;
        this.name = name;
        this.rarity = rarity;
        this.baseMaterial = baseMaterial;
        this.texture = texture;
        this.id = id;
        customMaterialKey=new NamespacedKey(plugin, "customMaterial");
        this.lore = lore;
    }

    public ItemStack createItem() {
        ItemStack material = new ItemStack(baseMaterial);
        ItemMeta meta = material.getItemMeta();
        meta.displayName(Component.text(name, TextColor.fromHexString(rarity)));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        NamespacedKey key = new NamespacedKey(plugin, "custom_item_id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.setItemModel(new NamespacedKey("terraria",texture));
        meta.getPersistentDataContainer().set(customMaterialKey, PersistentDataType.BYTE, (byte) 1);
        meta.setMaxStackSize(Integer.valueOf(64));
        material.setItemMeta(meta);
        return material;
    }

}
