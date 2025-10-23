package me.carson.terrariaTools.accesories;

import me.carson.terrariaTools.tools.Tool;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class AccessoryManager implements Listener {
    private final List<Accessory> accessoryItems = new ArrayList<>();

    public AccessoryManager(Plugin plugin) {
        //Adds items to manager
        accessoryItems.add(new Aglet(plugin));
        accessoryItems.add(new ObsidianSkull(plugin));
        accessoryItems.add(new RedBalloon(plugin));
        accessoryItems.add(new BandOfRegeneration(plugin));
        accessoryItems.add(new CloudInBottle(plugin));
        accessoryItems.add(new LuckyHorseshoe(plugin));

        //Adds listeners for special cases
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new LuckyHorseshoe(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CloudInBottle(plugin), plugin);
    }

    public void startAccessoryTask(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (ItemStack itemInv : player.getInventory().getContents()) {
                        if (itemInv != null) {
                            for (Accessory itemTool : accessoryItems) {
                                if (itemTool.isThisItem(itemInv)&& itemTool.isActivated(itemInv)) {
                                    itemTool.activateEffect(player);
                                }
                            }
                        }
                    }
                }
                }, 0L, 100L); // Runs every five seconds
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem= event.getItemDrop().getItemStack();
        if (!droppedItem.hasItemMeta()) return;
        for (Accessory item : accessoryItems) {
            if (item.isThisItem(droppedItem)) {
                item.deactivateEffect(player);
                item.setActivated(droppedItem,false);
                droppedItem.getItemMeta().setEnchantmentGlintOverride(false);
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();
        if(!player.isSneaking())return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        for (Accessory item : accessoryItems) {
            if (item.isThisItem(heldItem)) {
                if(!item.isActivated(heldItem)&&checkAmountActivated(player)){
                    item.setActivated(heldItem,true);
                    heldItem.getItemMeta().setEnchantmentGlintOverride(true);
                }else{
                    item.setActivated(heldItem,false);
                    heldItem.getItemMeta().setEnchantmentGlintOverride(false);
                }
            }
        }
    }

    public boolean checkAmountActivated(Player player){
        int counter=0;
        for (ItemStack itemInv : player.getInventory().getContents()) {
            if (itemInv != null) {
                for (Accessory itemTool : accessoryItems) {
                    if (itemTool.isThisItem(itemInv)&& itemTool.isActivated(itemInv)) {
                        counter++;
                    }
                }
            }
        }
        return counter < 5;
    }



}