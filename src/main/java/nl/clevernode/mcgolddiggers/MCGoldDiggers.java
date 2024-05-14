package nl.clevernode.mcgolddiggers;

import org.bukkit.plugin.java.JavaPlugin;

public final class MCGoldDiggers extends JavaPlugin {

    GoldDiggerWorld world = new GoldDiggerWorld(this);
    MiniGame game = new MiniGame(this.world);

    @Override
    public void onEnable() {
        getLogger().info("MCGoldDiggers enabled");

        GoldDiggerWorld world = this.world;
        world.initialize();

        EventListeners eventsListeners = new EventListeners(world, this.game);

        // register event listeners
        getServer()
                .getPluginManager()
                .registerEvents(eventsListeners, this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                world.getWorld().setTime(0L);
            }
        }, 100L, 100L);
    }

    @Override
    public void onDisable() {
        getLogger().info("MCGoldDiggers disabled");
    }

}
