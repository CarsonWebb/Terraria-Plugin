package me.carson.terrariaTools;

import me.carson.terrariaTools.accesories.AccessoryManager;
import me.carson.terrariaTools.accesories.CloudInBottle;
import me.carson.terrariaTools.tools.Cosmolight;
import me.carson.terrariaTools.tools.MomentumCapacitor;
import me.carson.terrariaTools.tools.RodOfDiscord;
import me.carson.terrariaTools.tools.ToolManager;
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

        RecipeManager recipeManager = new RecipeManager(this);
        recipeManager.registerRecipes();

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
