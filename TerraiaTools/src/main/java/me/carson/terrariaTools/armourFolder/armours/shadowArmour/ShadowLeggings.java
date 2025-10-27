package me.carson.terrariaTools.armourFolder.armours.shadowArmour;

import me.carson.terrariaTools.armourFolder.Armour;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ShadowLeggings extends Armour {

    public ShadowLeggings(Plugin plugin){
        super(plugin,"Shadow Leggings","#9696FF", Material.DIAMOND_LEGGINGS,"shadow_armour","ShadowLeggings",0,new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Increased movement speed and acceleration")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowLeggings(plugin).createItem();
    }
}
