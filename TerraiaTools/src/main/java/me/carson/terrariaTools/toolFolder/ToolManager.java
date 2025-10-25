package me.carson.terrariaTools.toolFolder;

import me.carson.terrariaTools.toolFolder.tools.Cosmolight;
import me.carson.terrariaTools.toolFolder.tools.MagicMirror;
import me.carson.terrariaTools.toolFolder.tools.MomentumCapacitor;
import me.carson.terrariaTools.toolFolder.tools.RodOfDiscord;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ToolManager implements Listener {
    private final List<Tool> toolItems = new ArrayList<>();

    public ToolManager(Plugin plugin) {
        toolItems.add(new Cosmolight(plugin));
        toolItems.add(new MomentumCapacitor(plugin));
        toolItems.add(new RodOfDiscord(plugin));
        toolItems.add((new MagicMirror(plugin)));
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        Player player = event.getPlayer();

        for (Tool item : toolItems) {
            if (item.isThisItem(heldItem)) {
                event.setCancelled(true);
                if(!player.hasCooldown(heldItem.getType())){
                    item.rightActivate(player);
                    player.setCooldown(heldItem.getType(), item.cooldown);
                }else{
                    item.cooldownEffect(player);
                }
            }
        }
    }
}