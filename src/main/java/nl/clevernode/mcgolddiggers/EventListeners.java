package nl.clevernode.mcgolddiggers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class EventListeners implements Listener {

    private final GoldDiggerWorld world;

    EventListeners(GoldDiggerWorld world) {
        super();
        this.world = world;
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
