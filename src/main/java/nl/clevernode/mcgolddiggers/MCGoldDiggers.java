package nl.clevernode.mcgolddiggers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCGoldDiggers extends JavaPlugin implements Listener {

    GoldDiggerWorld world = new GoldDiggerWorld();

    @Override
    public void onEnable() {
        getLogger().info("MCGoldDiggers is aangezet!");

        this.world.initialize();

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
        this.world.sendToStandardLocation(player);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(this.world.getStandardLocation());
    }

}
