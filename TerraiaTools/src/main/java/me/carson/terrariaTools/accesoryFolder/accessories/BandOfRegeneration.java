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

public class BandOfRegeneration extends Accessory implements Listener {

    public BandOfRegeneration(Plugin plugin){
        super(plugin,"Band of Regeneration","#9696FF", Material.REDSTONE_BLOCK,"band_of_regeneration","Band",new ArrayList<>(List.of(ChatColor.GRAY+"Slowly regenerates life",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0, true, false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.REGENERATION);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BandOfRegeneration(plugin).createItem();
    }

}
