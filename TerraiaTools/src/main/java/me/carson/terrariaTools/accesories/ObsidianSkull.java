package me.carson.terrariaTools.accesories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ObsidianSkull extends Accessory implements Listener {

    public ObsidianSkull(Plugin plugin){
        super(plugin,"Obsidian Skull","#96FF96", Material.OBSIDIAN,"obsidian_skull","Skull");
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120, 0, true, false));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new ObsidianSkull(plugin).createItem();
    }

}
