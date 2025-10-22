package me.carson.terrariaTools.tools;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.bukkit.*;

public class MomentumCapacitor extends Tool implements Listener {

    public MomentumCapacitor(Plugin plugin){
        super(plugin,"Momentum Capacitor","#FF96FF",Material.HEAVY_CORE,"momentum_capacitor","MomentumCapacitor",20);
    }

    @Override
    public void rightActivate(Player player) {
        // Launch the player in the direction they're looking
        Vector dir = player.getLocation().getDirection();
        dir.setY(0);
        dir.normalize().multiply(0.4);
        player.setVelocity(player.getVelocity().add(dir));

        // Optional: Add sound or particles
        player.getWorld().playSound(player.getLocation(), "minecraft:entity.breeze.wind_burst", 1f, 1f);
        player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MomentumCapacitor(plugin).createItem();
    }
}
