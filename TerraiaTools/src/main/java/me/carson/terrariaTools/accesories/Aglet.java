package me.carson.terrariaTools.accesories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Aglet extends Accessory implements Listener  {

    public Aglet(Plugin plugin){
        super(plugin,"Aglet","#9696FF",Material.COPPER_INGOT,"aglet","Aglet");
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 0, true, false));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new Aglet(plugin).createItem();
    }

}
