package me.carson.terrariaTools;

import me.carson.terrariaTools.TerrariaTools;
import me.carson.terrariaTools.accesories.Aglet;
import me.carson.terrariaTools.accesories.ObsidianSkull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TTCommand implements CommandExecutor, TabCompleter {

    private final TerrariaTools plugin;

    public TTCommand(TerrariaTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§eUsage: /tt <subcommand>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give" -> {
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /tt give <item>");
                    return true;
                }

                String itemName = args[1].toLowerCase();
                switch (itemName) {
                    case "cosmolight"-> {
                        player.getInventory().addItem(plugin.cosmolight.createItem());
                    }
                    case "rod_of_discord"-> {
                        player.getInventory().addItem(plugin.rodOfDiscord.createItem());
                    }
                    case "momentum_capacitor"-> {
                        player.getInventory().addItem(plugin.momentumCapacitor.createItem());
                    }
                    case "stormbow"-> {
                        player.getInventory().addItem(plugin.stormbow.createItem());
                    }
                    case "cloud_bottle"-> {
                        player.getInventory().addItem(plugin.cloudInBottle.createItem());
                    }
                    case "aglet"-> {
                        player.getInventory().addItem(Aglet.getItem(plugin));
                    }
                    case "obsidian_skull"-> {
                        player.getInventory().addItem(ObsidianSkull.getItem(plugin));
                    }
                    default -> player.sendMessage("§cUnknown item: " + itemName);
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // First argument: subcommands
            List<String> subCommands = Arrays.asList("give");
            StringUtil.copyPartialMatches(args[0], subCommands, completions);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            // Second argument: item names
            List<String> items = Arrays.asList("Cosmolight","Rod_of_Discord","Momentum_Capacitor","Stormbow","Cloud_bottle","Aglet","Obsidian_Skull");
            StringUtil.copyPartialMatches(args[1], items, completions);
        }

        return completions;
    }
}