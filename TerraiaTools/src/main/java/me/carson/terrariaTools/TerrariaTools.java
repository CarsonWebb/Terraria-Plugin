package me.carson.terrariaTools;

import me.carson.terrariaTools.accesoryFolder.AccessoryManager;
import me.carson.terrariaTools.recipieManagers.AccessoryRecipeManager;
import me.carson.terrariaTools.recipieManagers.ToolRecipeManager;
import me.carson.terrariaTools.toolFolder.ToolManager;
import me.carson.terrariaTools.weapons.Stormbow;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class TerrariaTools extends JavaPlugin {

    Stormbow stormbow;

    @Override
    public void onEnable() {
        stormbow=new Stormbow(this);

        AccessoryManager aManager = new AccessoryManager(this);
        aManager.startAccessoryTask(this);

        ToolManager tManager=new ToolManager(this);

        AccessoryRecipeManager accessoryRecipeManager = new AccessoryRecipeManager(this);
        accessoryRecipeManager.registerRecipes();
        ToolRecipeManager toolRecipeManager = new ToolRecipeManager(this);
        toolRecipeManager.registerRecipes();

        getServer().getPluginManager().registerEvents(aManager, this);
        getServer().getPluginManager().registerEvents(tManager, this);
        getServer().getPluginManager().registerEvents(new ResourcePackHandler(), this);

        TTCommand ttCommand = new TTCommand(this);
        new CraftBlockerListener(this);
        getServer().getPluginManager().registerEvents(new ItemPlaceListener(this), this);

        Objects.requireNonNull(getCommand("tt")).setExecutor(ttCommand);
        Objects.requireNonNull(getCommand("tt")).setTabCompleter(ttCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
