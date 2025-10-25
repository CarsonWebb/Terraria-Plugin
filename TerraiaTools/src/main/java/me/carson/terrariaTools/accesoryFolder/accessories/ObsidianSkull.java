package me.carson.terrariaTools.accesoryFolder.accessories;

import me.carson.terrariaTools.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ObsidianSkull extends Accessory implements Listener {

    public ObsidianSkull(Plugin plugin){
        super(plugin,"Obsidian Skull","#96FF96", Material.OBSIDIAN,"obsidian_skull","Skull",new ArrayList<>(List.of(ChatColor.GRAY+"Grants immunity to fire damage", ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120, 0, true, false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianSkull(plugin).createItem();
    }

}
