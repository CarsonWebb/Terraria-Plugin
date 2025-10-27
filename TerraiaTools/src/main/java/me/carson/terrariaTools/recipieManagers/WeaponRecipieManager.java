package me.carson.terrariaTools.recipieManagers;

import me.carson.terrariaTools.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaTools.toolFolder.tools.MagicMirror;
import me.carson.terrariaTools.weaponsFolder.weapons.LightsBane;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class WeaponRecipieManager {

    private final Plugin plugin;

    public WeaponRecipieManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerLightsBaneRecipe();
    }

    private void registerLightsBaneRecipe(){
        ItemStack bane=new LightsBane(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "LightsBane");
        ShapedRecipe recipe = new ShapedRecipe(key, bane);
        recipe.shape(" D "," D "," D ");
        recipe.setIngredient('D',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe);
    }
}
