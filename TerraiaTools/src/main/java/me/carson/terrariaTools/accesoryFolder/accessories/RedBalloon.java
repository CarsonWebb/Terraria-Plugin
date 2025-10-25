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

public class RedBalloon extends Accessory implements Listener {

    public RedBalloon(Plugin plugin){
        super(plugin,"Shiny Red Balloon","#9696FF", Material.RED_WOOL,"shiny_red_balloon","Balloon",new ArrayList<>(List.of(ChatColor.GRAY+"Increases jump height",ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 120, 0, true, false));
    }

    @Override
    public void deactivateEffect(Player player) {
        player.removePotionEffect(PotionEffectType.JUMP_BOOST);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new RedBalloon(plugin).createItem();
    }

}