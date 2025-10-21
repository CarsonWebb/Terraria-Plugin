package me.carson.terrariaTools.accesories;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RedBalloon extends Accessory implements Listener {

    public RedBalloon(Plugin plugin){
        super(plugin,"Shiny Red Balloon","#9696FF", Material.RED_WOOL,"shiny_red_balloon","Balloon",new NamespacedKey(plugin, "uncraftable"));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 120, 0, true, false));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new RedBalloon(plugin).createItem();
    }

}