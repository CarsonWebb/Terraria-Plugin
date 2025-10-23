package me.carson.terrariaTools.accesories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class LuckyHorseshoe extends Accessory implements Listener  {

    public LuckyHorseshoe(Plugin plugin){
        super(plugin,"Lucky Horseshoe","#9696FF",Material.GOLD_BLOCK,"lucky_horseshoe","LuckyHorseshoe",false);
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if ((event.getCause() == EntityDamageEvent.DamageCause.FALL)&&activated) {
            event.setCancelled(true);
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new LuckyHorseshoe(plugin).createItem();
    }

}
