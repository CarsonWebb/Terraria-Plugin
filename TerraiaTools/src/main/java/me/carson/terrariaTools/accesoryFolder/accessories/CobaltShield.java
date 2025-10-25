package me.carson.terrariaTools.accesoryFolder.accessories;

import com.destroystokyo.paper.event.entity.EntityKnockbackByEntityEvent;
import me.carson.terrariaTools.accesoryFolder.Accessory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CobaltShield extends Accessory implements Listener {

    public CobaltShield(Plugin plugin){
        super(plugin,"Cobalt Shield","#96FF96", Material.LAPIS_BLOCK,"cobalt_shield","CobaltShield",
                new ArrayList<>(List.of(
                        ChatColor.GRAY+"Grants immunity to knockback",
                        ChatColor.GRAY+"Shift Right Click to Activate")));
    }

    @Override
    public void activateEffect(Player player){

    }

    @Override
    public void deactivateEffect(Player player) {

    }

    @EventHandler
    public void onKnockback(EntityKnockbackByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (CobaltShield.this.isThisItem(itemInv)) {
                if (CobaltShield.this.isActivated(itemInv)) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }

    public static ItemStack getItem(Plugin plugin) {
        return new CobaltShield(plugin).createItem();
    }
}
