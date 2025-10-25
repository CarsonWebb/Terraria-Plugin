package me.carson.terrariaTools.recipieManagers;

import me.carson.terrariaTools.toolFolder.tools.Cosmolight;
import me.carson.terrariaTools.toolFolder.tools.MagicMirror;
import me.carson.terrariaTools.toolFolder.tools.MomentumCapacitor;
import me.carson.terrariaTools.toolFolder.tools.RodOfDiscord;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class ToolRecipeManager {

    private final Plugin plugin;

    public ToolRecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        registerMirrorRecipe();
        registerCosmolightRecipe();
        registerCapacitorRecipe();
        registerRodRecipe();
    }

    private void registerMirrorRecipe(){
        ItemStack mirror=new MagicMirror(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MagicMirror");
        ShapedRecipe recipe = new ShapedRecipe(key, mirror);
        recipe.shape("IDI","IGI","IDI");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }
    private void registerCosmolightRecipe(){
        ItemStack cosmolight=new Cosmolight(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "Cosmolight");
        ShapedRecipe recipe = new ShapedRecipe(key, cosmolight);
        recipe.shape("EGN","GCG","PGS");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('G', Material.GLASS);
        recipe.setIngredient('E', Material.END_STONE);
        recipe.setIngredient('N', Material.NETHERRACK);
        recipe.setIngredient('S', Material.SUNFLOWER);
        recipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.addRecipe(recipe);
    }
    private void registerCapacitorRecipe(){
        ItemStack capacitor=new MomentumCapacitor(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "MomentumCapacitor");
        ShapedRecipe recipe = new ShapedRecipe(key, capacitor);
        recipe.shape("WWW","WNW","WWW");
        recipe.setIngredient('W', Material.WIND_CHARGE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(recipe);
    }
    private void registerRodRecipe(){
        ItemStack rod=new RodOfDiscord(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "RodOfDiscord");
        ShapedRecipe recipe = new ShapedRecipe(key, rod);
        recipe.shape("ECE","ENE","ENE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('C', Material.CHORUS_FRUIT);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(recipe);
    }
}
