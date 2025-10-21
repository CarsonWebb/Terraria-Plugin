package me.carson.terrariaTools.accesories;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BandOfRegeneration extends Accessory implements Listener {

    public BandOfRegeneration(Plugin plugin){
        super(plugin,"Band of Regeneration","#9696FF", Material.REDSTONE_BLOCK,"band_of_regeneration","Band",new NamespacedKey(plugin, "uncraftable"));
    }

    @Override
    public void activateEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0, true, false));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new BandOfRegeneration(plugin).createItem();
    }

}
