package me.carson.terrariaTools.tools;

import me.carson.terrariaTools.accesories.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ToolManager implements Listener {
    private final List<Tool> items = new ArrayList<>();

    public ToolManager(Plugin plugin) {
        //items.add(new Cosmolight(plugin));
        //items.add(new MomentumCapacitor(plugin));
        //items.add(new RodOfDiscord(plugin));
    }

}