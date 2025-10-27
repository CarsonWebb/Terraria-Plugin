package me.carson.terrariaTools.weaponsFolder;

import me.carson.terrariaTools.weaponsFolder.weapons.LightsBane;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WeaponManager implements Listener {

    private final List<Weapon> weaponItems = new ArrayList<>();

    public WeaponManager(Plugin plugin) {
        weaponItems.add(new LightsBane(plugin));
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        Player player = event.getPlayer();

        for (Weapon item : weaponItems) {
            if (item.isThisItem(heldItem)) {
                event.setCancelled(true);
                if(!player.hasCooldown(heldItem.getType())){
                    item.leftActivate(player);
                    player.setCooldown(heldItem.getType(), item.cooldown);
                }
            }
        }
    }
}
