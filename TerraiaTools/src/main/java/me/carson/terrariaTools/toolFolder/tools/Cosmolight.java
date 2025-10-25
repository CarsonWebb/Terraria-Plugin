package me.carson.terrariaTools.toolFolder.tools;

import me.carson.terrariaTools.toolFolder.Tool;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class Cosmolight extends Tool implements Listener{

    public Cosmolight(Plugin plugin){
        super(plugin,"Cosmolight","#FF9696",Material.CLOCK,"cosmolight","Cosmolight",20,new ArrayList<>(List.of(
                ChatColor.GRAY+"Advances time immediately to the next dawn, noon, dusk or midnight")));
    }

    @Override
    public void rightActivate(Player player){
        if(!player.hasCooldown(Material.CLOCK)){
            nextTime(player.getWorld());
            player.playSound(player.getLocation(),"terraria:cosmolight_use", 1.5f, 1.5f);
        }

    }

    @Override
    public void cooldownEffect(Player player) {
        //N/A
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
