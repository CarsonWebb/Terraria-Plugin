package me.carson.terrariaTools.accesories;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
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
import org.bukkit.util.Vector;

public class CloudInBottle implements Listener {

    private final NamespacedKey key;
    private final NamespacedKey uncraftableKey;
    private boolean activated=false;

    public CloudInBottle(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "cloud");
        this.uncraftableKey = new NamespacedKey(plugin, "uncraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack createItem() {
        ItemStack cloud = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta meta = cloud.getItemMeta();
        meta.displayName(Component.text("Cloud in a Bottle", TextColor.fromHexString("#9696FF")));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","cloud_in_a_bottle"));
        meta.setMaxStackSize(Integer.valueOf(1));
        cloud.setItemMeta(meta);
        return cloud;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();
        ItemStack glass = player.getInventory().getItemInMainHand();
        double height=player.getLocation().getY();
        if(glass.getType() == Material.GLASS_BOTTLE&&((height>=180)&&(height<=200))){
            glass.setAmount(glass.getAmount() - 1);
            player.getInventory().addItem(createItem());
        }
    }

    @EventHandler
    public void onShiftRightClick(PlayerInteractEvent event){
        if (event.getItem() == null) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        Player player = event.getPlayer();
        if(!player.isSneaking()) return;

        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (!data.has(key, PersistentDataType.BYTE)) return;

        if(!activated){
            meta.setEnchantmentGlintOverride(true);
            item.setItemMeta(meta);
            //meta.addEnchant(org.bukkit.enchantments.Enchantment.LUCK, 1, true);
            //meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
            activated=true;
        }else{
            meta.setEnchantmentGlintOverride(false);
            item.setItemMeta(meta);
            activated=false;
        }
    }

    @EventHandler
    public void onJump(PlayerJumpEvent event){
        Player player = event.getPlayer();
        if(!player.getInventory().contains(createItem())){
            return;
        }
        if(!player.isOnGround()){
            player.setVelocity(player.getVelocity().add(new Vector(0,1,0)));
            player.getWorld().playSound(player.getLocation(), "minecraft:entity.breeze.wind_burst", 1f, 1f);
            player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
        }
    }

}
