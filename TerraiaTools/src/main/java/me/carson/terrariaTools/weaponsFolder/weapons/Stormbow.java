package me.carson.terrariaTools.weaponsFolder.weapons;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class Stormbow implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey key;
    private final NamespacedKey uncraftableKey;

    public Stormbow(JavaPlugin plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "stormbow");
        this.uncraftableKey = new NamespacedKey(plugin, "uncraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack createItem() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.displayName(Component.text("Daedalus Stormbow", TextColor.fromHexString("#D2A0FF")));
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","daedalus_stormbow"));
        meta.setMaxStackSize(Integer.valueOf(1));
        bow.setItemMeta(meta);
        return bow;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;
        World world=player.getWorld();

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        if (!data.has(key, PersistentDataType.BYTE)) return; // Not our item

        event.setCancelled(true); // prevent default interaction

        // Ray trace up to 150 blocks
        RayTraceResult result = world.rayTraceBlocks(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                150,
                FluidCollisionMode.NEVER,
                true
        );

        if (result == null || result.getHitPosition() == null) {
            return;
        }

        Vector hit = result.getHitPosition();
        Location target = new Location(world, hit.getX(), hit.getY()+20, hit.getZ());

        for(int i =0;i<3;i++){
            Arrow arrow=world.spawnArrow(target,new Vector(0,-1,0),2,10);
            arrow.setDamage(5.0);
        }
    }

}

