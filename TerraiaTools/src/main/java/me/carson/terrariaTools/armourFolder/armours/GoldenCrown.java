package me.carson.terrariaTools.armourFolder.armours;

import me.carson.terrariaTools.armourFolder.Armour;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class GoldenCrown extends Armour {

    public GoldenCrown(Plugin plugin){
        super(plugin,"Golden Crown","#FFFFFF", Material.DIAMOND_CHESTPLATE,"iron_gem","GoldenCrown",0,new ArrayList<>(List.of(ChatColor.GRAY+"",ChatColor.GRAY+"")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new GoldenCrown(plugin).createItem();
    }
}
