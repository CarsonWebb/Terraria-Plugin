package me.carson.terrariaTools.armourFolder;

import me.carson.terrariaTools.accesoryFolder.Accessory;
import me.carson.terrariaTools.armourFolder.armours.GoldenCrown;
import me.carson.terrariaTools.armourFolder.armours.shadowArmour.ShadowGreaves;
import me.carson.terrariaTools.armourFolder.armours.shadowArmour.ShadowHelmet;
import me.carson.terrariaTools.armourFolder.armours.shadowArmour.ShadowLeggings;
import me.carson.terrariaTools.armourFolder.armours.shadowArmour.ShadowScalemail;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ArmourManager implements Listener {

    private final List<Armour> armourItems = new ArrayList<>();

    public ArmourManager(Plugin plugin) {
        armourItems.add(new GoldenCrown(plugin));
        armourItems.add(new ShadowHelmet(plugin));
        armourItems.add(new ShadowScalemail(plugin));
        armourItems.add(new ShadowLeggings(plugin));
        armourItems.add(new ShadowGreaves(plugin));
    }
}
