package me.carson.terrariaTools.materialsFolder.materials;

import me.carson.terrariaTools.materialsFolder.Material;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class DemoniteBar extends Material {

    public DemoniteBar(Plugin plugin) {
        super(plugin,"Demonite Bar","#9696FF", org.bukkit.Material.IRON_BLOCK,"demonite_bar","DemoniteBar", new ArrayList<>(List.of(ChatColor.GRAY+"Pulsing with dark energy")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new DemoniteBar(plugin).createItem();
    }
}
