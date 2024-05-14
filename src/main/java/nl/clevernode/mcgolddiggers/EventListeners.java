package nl.clevernode.mcgolddiggers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class EventListeners implements Listener {

    private final GoldDiggerWorld world;
    private final MiniGame game;

    EventListeners(GoldDiggerWorld world, MiniGame game) {
        super();
        this.world = world;
        this.game = game;
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

        if (isUnbreakable(block)) {
            event.setCancelled(true);
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

        this.game.teleportToMinigame(event.getPlayer());
    }

    private Boolean isUnbreakable(Block block) {
        return getBooleanMetadata(block, "UNBREAKABLE");
    }

    private Boolean isStartSign(Block block) {
        return getBooleanMetadata(block, "START_SIGN");
    }

    private Boolean getBooleanMetadata(Block block, String name) {
        if (block == null) {
            return false;
        }

        List<MetadataValue> metadata = block.getMetadata(name);

        if (metadata.isEmpty()) {
            return false;
        }

        MetadataValue data = block.getMetadata(name).get(0);

        return data != null && data.asBoolean();
    }

}