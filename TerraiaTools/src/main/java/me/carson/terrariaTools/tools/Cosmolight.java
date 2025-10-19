package me.carson.terrariaTools.tools;


import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class Cosmolight implements Listener{

    private final NamespacedKey key;

    public Cosmolight(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "cosmolight");
        // Register event listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Creates the custom clock item with an identifying tag.
     */
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Cosmolight", TextColor.fromHexString("#FF9696")));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","cosmolight"));
        meta.setMaxStackSize(Integer.valueOf(1));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        // Check if it's our custom clock
        if (!data.has(key, PersistentDataType.BYTE)) return;

        Player player = event.getPlayer();

        if (player.hasCooldown(item.getType())) {
            return;
        }

        // === Do stuff here ===
        nextTime(player.getWorld());
        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1.5f, 1.5f);
        player.setCooldown(item.getType(), 20);
    }

    public void nextTime(World world){
        long time = world.getTime();
        if ((0<=time)&&(time<6000)){
            world.setTime(6000);
        } else if ((6000<=time)&&(time<12000)) {
            world.setTime(12000);
        } else if ((12000<=time)&&(time<18000)) {
            world.setTime(18000);
        } else if ((18000<=time)&&(time<24000)) {
            world.setTime(24000);
        }
    }
}
