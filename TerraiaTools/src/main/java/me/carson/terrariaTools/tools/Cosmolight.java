package me.carson.terrariaTools.tools;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class Cosmolight extends Tool implements Listener{

    public Cosmolight(Plugin plugin){
        super(plugin,"Cosmolight","#FF9696",Material.CLOCK,"cosmolight","Cosmolight",20);
    }

    @Override
    public void rightActivate(Player player){
        if(!player.hasCooldown(Material.CLOCK)){
            nextTime(player.getWorld());
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 1.5f, 1.5f);
        }

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

    public static ItemStack getItem(Plugin plugin) {
        return new Cosmolight(plugin).createItem();
    }
}
