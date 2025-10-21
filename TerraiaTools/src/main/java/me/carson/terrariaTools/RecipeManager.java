package me.carson.terrariaTools;

import me.carson.terrariaTools.accesories.Aglet;
import me.carson.terrariaTools.accesories.BandOfRegeneration;
import me.carson.terrariaTools.accesories.ObsidianSkull;
import me.carson.terrariaTools.accesories.RedBalloon;
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
        registerBandOfRegenerationRecipe();
        registerRedBalloonRecipe();
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

    private void registerRedBalloonRecipe(){
        ItemStack balloon=new RedBalloon(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "balloon");
        ShapedRecipe recipe = new ShapedRecipe(key, balloon);
        recipe.shape(" W "," S "," S ");
        recipe.setIngredient('W', Material.RED_WOOL);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe);
    }

    private void registerBandOfRegenerationRecipe(){
        ItemStack band=new BandOfRegeneration(plugin).createItem();
        NamespacedKey key = new NamespacedKey(plugin, "band");
        ShapedRecipe recipe = new ShapedRecipe(key, band);
        recipe.shape("RRR","R R","RRR");
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        Bukkit.addRecipe(recipe);
    }

}
