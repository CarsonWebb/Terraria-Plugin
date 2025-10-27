package me.carson.terrariaTools.recipieManagers;

import me.carson.terrariaTools.materialsFolder.materials.DemoniteBar;
import me.carson.terrariaTools.toolFolder.tools.MagicMirror;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class MaterialRecipieManager {

    private final Plugin plugin;

    public MaterialRecipieManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerDemoniteBarRecipe();
    }

    private void registerDemoniteBarRecipe(){
        ItemStack demoniteBar=new DemoniteBar(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "DemoniteBar");
        ShapedRecipe recipe = new ShapedRecipe(key, demoniteBar);
        recipe.shape(" S ","SIS"," S ");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.SOUL_SAND);
        Bukkit.addRecipe(recipe);
    }

}
