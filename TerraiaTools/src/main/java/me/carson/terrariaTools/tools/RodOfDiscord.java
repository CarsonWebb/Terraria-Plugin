package me.carson.terrariaTools.tools;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class RodOfDiscord extends Tool implements Listener {

    public RodOfDiscord(Plugin plugin){
        super(plugin,"Rod Of Discord","#96FF0A",Material.BLAZE_ROD,"rod_of_discord","RodOfDiscord",120);
    }

    @Override
    public void rightActivate(Player player) {
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

        if (player.hasCooldown(Material.BLAZE_ROD)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,1,0,false,false,false));
        }

        player.teleport(target, PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.playSound(target, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
        player.spawnParticle(Particle.PORTAL, target, 50, 0.5, 0.5, 0.5, 0.1);
    }

    @Override
    public void cooldownEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,1,0,false,false,false));
    }

    public static ItemStack getItem(Plugin plugin) {
        return new RodOfDiscord(plugin).createItem();
    }
}
