package me.carson.terrariaTools;

import me.carson.terrariaTools.accesories.Aglet;
import me.carson.terrariaTools.accesories.ObsidianSkull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class RecipeManager {

    private final Plugin plugin;

    public RecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerAgletRecipe();
        registerObsidianSkullRecipe();
    }

    private void registerAgletRecipe(){
        ItemStack aglet=new Aglet(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "aglet");
        ShapedRecipe recipe = new ShapedRecipe(key, aglet);
        recipe.shape(" C ","C C"," C ");
        recipe.setIngredient('C', Material.COPPER_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerObsidianSkullRecipe(){
        ItemStack skull=new ObsidianSkull(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "skull");
        ShapedRecipe recipe = new ShapedRecipe(key, skull);
        recipe.shape("OOO","OOO"," O ");
        recipe.setIngredient('O', Material.OBSIDIAN);
        Bukkit.addRecipe(recipe);
    }

}
