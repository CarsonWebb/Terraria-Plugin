package me.carson.terrariaTools.toolFolder.tools;

import me.carson.terrariaTools.toolFolder.Tool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MomentumCapacitor extends Tool implements Listener {

    public MomentumCapacitor(Plugin plugin){
        super(plugin,"Momentum Capacitor","#FF96FF",Material.HEAVY_CORE,"momentum_capacitor","MomentumCapacitor",0,new ArrayList<>(List.of(
                ChatColor.GRAY+"Accelerates your horizontal speed")));
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

    @Override
    public void cooldownEffect(Player player) {
        //N/A
    }

    public static ItemStack getItem(Plugin plugin) {
        return new MomentumCapacitor(plugin).createItem();
    }
}
