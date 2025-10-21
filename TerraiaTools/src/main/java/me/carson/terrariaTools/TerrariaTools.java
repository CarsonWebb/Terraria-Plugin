package me.carson.terrariaTools;

import me.carson.terrariaTools.accesories.AccessoryManager;
import me.carson.terrariaTools.accesories.Aglet;
import me.carson.terrariaTools.accesories.CloudInBottle;
import me.carson.terrariaTools.tools.Cosmolight;
import me.carson.terrariaTools.tools.MomentumCapacitor;
import me.carson.terrariaTools.tools.RodOfDiscord;
import me.carson.terrariaTools.weapons.Stormbow;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class TerrariaTools extends JavaPlugin {

    Cosmolight cosmolight;
    RodOfDiscord rodOfDiscord;
    MomentumCapacitor momentumCapacitor;
    Stormbow stormbow;
    CloudInBottle cloudInBottle;
    Aglet aglet;

    @Override
    public void onEnable() {
        cosmolight = new Cosmolight(this);
        rodOfDiscord=new RodOfDiscord(this);
        momentumCapacitor=new MomentumCapacitor(this);
        stormbow=new Stormbow(this);

        AccessoryManager manager = new AccessoryManager(this);
        manager.startAccessoryTask(this);

        RecipeManager recipeManager = new RecipeManager(this);
        recipeManager.registerRecipes();

        getServer().getPluginManager().registerEvents(manager, this);
        getServer().getPluginManager().registerEvents(new ResourcePackHandler(), this);

        TTCommand ttCommand = new TTCommand(this);
        new CraftBlockerListener(this);

        Objects.requireNonNull(getCommand("tt")).setExecutor(ttCommand);
        Objects.requireNonNull(getCommand("tt")).setTabCompleter(ttCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
