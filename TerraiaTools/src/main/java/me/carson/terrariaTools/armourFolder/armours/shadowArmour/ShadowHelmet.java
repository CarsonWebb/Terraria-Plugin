package me.carson.terrariaTools.armourFolder.armours.shadowArmour;

import me.carson.terrariaTools.armourFolder.Armour;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ShadowHelmet extends Armour {

    public ShadowHelmet(Plugin plugin){
        super(plugin,"Shadow Helmet","#9696FF", Material.DIAMOND_HELMET,"shadow_armour","ShadowHelmet",0,new ArrayList<>(List.of(ChatColor.GRAY+"Set Bonus: Increased movement speed and acceleration")));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ShadowHelmet(plugin).createItem();
    }
}
