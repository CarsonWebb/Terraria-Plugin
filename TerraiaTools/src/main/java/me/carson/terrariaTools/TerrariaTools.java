package me.carson.terrariaTools;

import me.carson.terrariaTools.ListenersHandler.CraftBlockerListener;
import me.carson.terrariaTools.ListenersHandler.CustomCraftingListener;
import me.carson.terrariaTools.ListenersHandler.ItemPlaceListener;
import me.carson.terrariaTools.ListenersHandler.ResourcePackHandler;
import me.carson.terrariaTools.accesoryFolder.AccessoryManager;
import me.carson.terrariaTools.armourFolder.ArmourManager;
import me.carson.terrariaTools.recipieManagers.AccessoryRecipeManager;
import me.carson.terrariaTools.recipieManagers.MaterialRecipieManager;
import me.carson.terrariaTools.recipieManagers.ToolRecipeManager;
import me.carson.terrariaTools.recipieManagers.WeaponRecipieManager;
import me.carson.terrariaTools.toolFolder.ToolManager;
import me.carson.terrariaTools.weaponsFolder.WeaponManager;
import me.carson.terrariaTools.weaponsFolder.weapons.Stormbow;
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

        ArmourManager armourManager = new ArmourManager(this);

        WeaponManager weaponManager=new WeaponManager(this);

        AccessoryRecipeManager accessoryRecipeManager = new AccessoryRecipeManager(this);
        accessoryRecipeManager.registerRecipes();
        ToolRecipeManager toolRecipeManager = new ToolRecipeManager(this);
        toolRecipeManager.registerRecipes();
        MaterialRecipieManager materialRecipieManager = new MaterialRecipieManager(this);
        materialRecipieManager.registerRecipes();
        WeaponRecipieManager weaponRecipieManager = new WeaponRecipieManager(this);
        weaponRecipieManager.registerRecipes();

        getServer().getPluginManager().registerEvents(aManager, this);
        getServer().getPluginManager().registerEvents(tManager, this);
        getServer().getPluginManager().registerEvents(armourManager, this);
        getServer().getPluginManager().registerEvents(weaponManager, this);
        getServer().getPluginManager().registerEvents(new ResourcePackHandler(), this);

        TTCommand ttCommand = new TTCommand(this);
        new CraftBlockerListener(this);
        new CustomCraftingListener(this);
        getServer().getPluginManager().registerEvents(new ItemPlaceListener(this), this);

        Objects.requireNonNull(getCommand("tt")).setExecutor(ttCommand);
        Objects.requireNonNull(getCommand("tt")).setTabCompleter(ttCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
