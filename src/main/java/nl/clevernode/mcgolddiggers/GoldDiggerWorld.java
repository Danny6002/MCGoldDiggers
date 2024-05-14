package nl.clevernode.mcgolddiggers;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getLogger;

public class GoldDiggerWorld {

    private final String worldName = "MCGoldDiggers";

    private JavaPlugin plugin;

    GoldDiggerWorld(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private World getWorld() {
        return Bukkit.getWorld(this.worldName);
    }

    public void setupSign(Block block) {
        BlockState state = block.getState();

        if (state instanceof Sign) {
            Sign sign = (Sign) state;

            SignSide side = sign.getSide(Side.BACK);
            side.setLine(0, "Welkom bij");
            side.setLine(1, "MC Gold Diggers!");
            side.setLine(3, "BEGIN SPEL");

            sign.setMetadata("START_SIGN", new FixedMetadataValue(this.plugin, true));

            sign.update();
        }
    }

    public void createStartingPoint() {
        Block startingBlock = this.getStartPointLocation().getBlock();
        startingBlock.setType(Material.OAK_SIGN);

        setupSign(startingBlock);

        getLogger().info("Spawn starting point");
    }

    public void initialize() {
        World world = getWorld();

        // unload current world
        if (world != null) {
            world.setKeepSpawnInMemory(false);
        }
        Bukkit.getServer().unloadWorld(world, false);

        // initialize the flat world
        Bukkit.createWorld(
                new WorldCreator(this.worldName)
                        .type(WorldType.FLAT)
                        .generateStructures(false)
                        .generatorSettings("{ \"layers\": [{\"block\": \"stone\", \"height\": 1}, {\"block\": \"grass_block\", \"height\": 1}], \"biome\":\"plains\" }")
        );

        this.createStartingPoint();
    }

    public Location getStartPointLocation() {
        return new Location(this.getWorld(), 0, -62, 2);
    }

    public Location getStandardLocation() {
        return new Location(this.getWorld(), 0, -60, 0);
    }

    public void sendToStandardLocation(Player player) {
        player.teleport(this.getStandardLocation());
    }

}
