package me.carson.terrariaTools;

import me.carson.terrariaTools.accesories.*;
import me.carson.terrariaTools.tools.Cosmolight;
import me.carson.terrariaTools.tools.MomentumCapacitor;
import me.carson.terrariaTools.tools.RodOfDiscord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TTCommand implements CommandExecutor, TabCompleter {

    private final TerrariaTools plugin;

    public TTCommand(TerrariaTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
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
                        player.getInventory().addItem(Cosmolight.getItem(plugin));
                    }
                    case "rod_of_discord"-> {
                        player.getInventory().addItem(RodOfDiscord.getItem(plugin));
                    }
                    case "momentum_capacitor"-> {
                        player.getInventory().addItem(MomentumCapacitor.getItem(plugin));
                    }
                    case "stormbow"-> {
                        player.getInventory().addItem(plugin.stormbow.createItem());
                    }
                    case "cloud_bottle"-> {
                        player.getInventory().addItem(CloudInBottle.getItem(plugin));
                    }
                    case "aglet"-> {
                        player.getInventory().addItem(Aglet.getItem(plugin));
                    }
                    case "obsidian_skull"-> {
                        player.getInventory().addItem(ObsidianSkull.getItem(plugin));
                    }
                    case "band_of_regeneration"-> {
                        player.getInventory().addItem(BandOfRegeneration.getItem(plugin));
                    }
                    case "red_balloon"-> {
                        player.getInventory().addItem(RedBalloon.getItem(plugin));
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
            List<String> items = Arrays.asList("Cosmolight","Rod_of_Discord","Momentum_Capacitor","Stormbow","Cloud_bottle","Aglet","Obsidian_Skull","red_balloon","band_of_regeneration");
            StringUtil.copyPartialMatches(args[1], items, completions);
        }

        return completions;
    }
}