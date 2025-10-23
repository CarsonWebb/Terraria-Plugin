package me.carson.terrariaTools.accesories;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class CloudInBottle extends Accessory implements Listener {

    private boolean active;

    public CloudInBottle(Plugin plugin){
        super(plugin,"Cloud in a Bottle","#9696FF",Material.GLASS_BOTTLE,"cloud_in_a_bottle","CloudInBottle",false);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();
        ItemStack glass = player.getInventory().getItemInMainHand();
        double height=player.getLocation().getY();
        if(glass.getType() == Material.GLASS_BOTTLE&&((height>=180)&&(height<=200))){
            glass.setAmount(glass.getAmount() - 1);
            player.getInventory().addItem(createItem());
        }
    }

    @Override
    public void activateEffect(Player player) {
        active=true;
    }

    @Override
    public void deactivateEffect(Player player) {
        active=false;
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event){
        if(active){
            event.getPlayer().setAllowFlight(true);
        }
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        // Only apply to survival or adventure mode
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
            return;

        // Prevent normal flight behavior
        event.setCancelled(true);
        if(player.getAllowFlight()&&active){
            player.setVelocity(player.getLocation().getDirection().multiply(1.0).setY(1.0));
            player.getWorld().playSound(player.getLocation(), "minecraft:entity.firework_rocket.launch", 1f, 1.5f);
        }

        // Perform the "double jump"
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isOnGround()){
            event.getPlayer().setAllowFlight(false);
        }
    }



    public static ItemStack getItem(Plugin plugin) {
        return new CloudInBottle(plugin).createItem();
    }
}
