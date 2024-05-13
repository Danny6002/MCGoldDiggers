package nl.clevernode.mcgolddiggers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCGoldDiggers extends JavaPlugin implements Listener {

    private String worldName = "leuke_naam";

    @Override
    public void onEnable() {
        getLogger().info("MCGoldDiggers is aangezet!");

        // initialize the flat world
        Bukkit.createWorld(new WorldCreator(this.worldName).type(WorldType.FLAT));

        // register event listeners
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MCGoldDiggers onDisable has been invoked!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.sendToStandardWorld(player);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(this.getStandardLocation());
    }

    private Location getStandardLocation() {
        World world = Bukkit.getWorld(this.worldName);
        return new Location(world, 0, 0, 0);
    }

    private void sendToStandardWorld(Player player) {
        player.teleport(this.getStandardLocation());
    }
}
