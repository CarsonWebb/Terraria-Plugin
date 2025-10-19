package me.carson.terrariaTools.tools;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class RodOfDiscord implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey key;
    private final NamespacedKey uncraftableKey;

    public RodOfDiscord(JavaPlugin plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "rod_of_discord");
        this.uncraftableKey = new NamespacedKey(plugin, "uncraftable");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // Create the custom item
    public ItemStack createItem() {
        ItemStack rod = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = rod.getItemMeta();
        meta.displayName(Component.text("Rod of Discord", TextColor.fromHexString("#96FF0A")));
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        meta.getPersistentDataContainer().set(uncraftableKey, PersistentDataType.BYTE, (byte) 1);
        meta.setItemModel(new NamespacedKey("terraria","rod_of_discord"));
        meta.setMaxStackSize(Integer.valueOf(1));
        rod.setItemMeta(meta);
        return rod;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        if (!data.has(key, PersistentDataType.BYTE)) return; // Not our item

        event.setCancelled(true); // prevent default interaction

        // Ray trace up to 50 blocks
        RayTraceResult result = player.getWorld().rayTraceBlocks(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                150,
                FluidCollisionMode.NEVER,
                true
        );

        if (result == null || result.getHitPosition() == null) {
            return;
        }

        // Teleport to just above the hit block
        Vector hit = result.getHitPosition();
        Location target = new Location(player.getWorld(), hit.getX(), hit.getY() + 1, hit.getZ());
        target.setYaw(player.getLocation().getYaw());
        target.setPitch(player.getLocation().getPitch());

        // Safety: don’t teleport into solid block
        if (target.getBlock().getType().isSolid()) {
            target.add(0, 1, 0);
        }

        if (player.hasCooldown(item.getType())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,1,0,false,false,false));
        }

        player.teleport(target, PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.playSound(target, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
        player.spawnParticle(Particle.PORTAL, target, 50, 0.5, 0.5, 0.5, 0.1);

        player.setCooldown(item.getType(), 120);
    }
}
