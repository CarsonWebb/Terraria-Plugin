package me.carson.terrariaTools.accesoryFolder;

import me.carson.terrariaTools.accesoryFolder.accessories.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AccessoryManager implements Listener {
    private final List<Accessory> accessoryItems = new ArrayList<>();
    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    public AccessoryManager(Plugin plugin) {
        //Adds items to manager
        accessoryItems.add(new Aglet(plugin));
        accessoryItems.add(new ObsidianSkull(plugin));
        accessoryItems.add(new RedBalloon(plugin));
        accessoryItems.add(new BandOfRegeneration(plugin));
        accessoryItems.add(new CloudInBottle(plugin));
        accessoryItems.add(new LuckyHorseshoe(plugin));
        accessoryItems.add(new CobaltShield(plugin));

        //Adds listeners for special cases
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginManager().registerEvents(new LuckyHorseshoe(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CloudInBottle(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new CobaltShield(plugin), plugin);
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

    public void deactivateItem(ItemStack itemChecked,Player player){
        if (!itemChecked.hasItemMeta()) return;
        for (Accessory item : accessoryItems) {
            if (item.isThisItem(itemChecked)) {
                item.deactivateEffect(player);
                item.setActivated(itemChecked,false);
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem= event.getItemDrop().getItemStack();
        deactivateItem(droppedItem,player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory topInv = event.getView().getTopInventory();
        Inventory clickedInv = event.getClickedInventory();
        Inventory playerInv= player.getInventory();

        // Manual placement
        if (clickedInv != null && clickedInv.equals(topInv)) {
            ItemStack cursor = event.getCursor();
            if (!cursor.getType().isAir()) {
                deactivateItem(cursor,player);
            }
        }
        // Shift-click movement
        if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY && clickedInv != null && clickedInv.equals(playerInv)) {
            ItemStack item = event.getCurrentItem();
            if (item != null && !item.getType().isAir()) {
                deactivateItem(item,player);
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();

        //handles rapid clicks
        long currentTime = System.currentTimeMillis();
        long lastTime = lastClickTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastTime < 500) {
            return; // Ignore repeated right-clicks
        }
        lastClickTime.put(player.getUniqueId(), currentTime);


        if(!player.isSneaking())return;
        ItemStack heldItem= event.getItem();
        if (heldItem == null) return;
        if (!heldItem.hasItemMeta()) return;
        for (Accessory item : accessoryItems) {
            if (item.isThisItem(heldItem)) {
                if(item.isActivated(heldItem)){
                    item.setActivated(heldItem,false);
                }else if(checkAmountActivated(player)){
                    item.setActivated(heldItem,true);
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
        if (counter >= 5){
            player.sendMessage(ChatColor.RED+"Can not have more than 5 active Accessories");
        }
        return counter < 5;
    }



}