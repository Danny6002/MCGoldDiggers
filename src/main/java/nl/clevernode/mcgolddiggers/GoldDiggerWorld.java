package nl.clevernode.mcgolddiggers;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class GoldDiggerWorld {

    private final String worldName = "MCGoldDiggers";

    private World getWorld() {
        return Bukkit.getWorld(this.worldName);
    }

    public void initialize() {
        // unload current world
        Bukkit.getServer().unloadWorld(this.getWorld(), false);

        // initialize the flat world
        Bukkit.createWorld(
                new WorldCreator(this.worldName)
                        .type(WorldType.FLAT)
                        .generateStructures(false)
                        .generatorSettings("{ \"layers\": [{\"block\": \"stone\", \"height\": 1}, {\"block\": \"grass_block\", \"height\": 1}], \"biome\":\"plains\" }")
        );
    }

    public Location getStandardLocation() {
        return new Location(this.getWorld(), 0, -60, 0);
    }

    public void sendToStandardLocation(Player player) {
        player.teleport(this.getStandardLocation());
    }

}
