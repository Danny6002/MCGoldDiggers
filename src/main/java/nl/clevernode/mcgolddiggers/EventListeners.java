package nl.clevernode.mcgolddiggers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

import static org.bukkit.Bukkit.getLogger;

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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (isStartSign(block)) {
            event.setCancelled(true);
            this.world.setupSign(block);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getPlayer().isSneaking()) {
            return;
        }

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null || !isStartSign(clickedBlock)) {
            return;
        }

        event.setCancelled(true);
    }

    private Boolean isStartSign(Block block) {
        if (block == null) {
            return false;
        }

        List<MetadataValue> metadata = block.getMetadata("START_SIGN");

        if (metadata.isEmpty()) {
            return false;
        }

        MetadataValue data = block.getMetadata("START_SIGN").get(0);

        return data != null && data.asBoolean();
    }

}