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


public class Aglet extends Accessory implements Listener  {

    public Aglet(Plugin plugin){
        super(plugin,"Aglet","#9696FF",Material.COPPER_INGOT,"aglet","Aglet",new ArrayList<>(List.of(ChatColor.GRAY+"Increased movement speed",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 0, false, false,false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.SPEED);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Aglet(plugin).createItem();
    }

}
