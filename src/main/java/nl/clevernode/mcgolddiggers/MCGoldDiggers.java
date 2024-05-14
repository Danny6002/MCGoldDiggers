package nl.clevernode.mcgolddiggers;

import org.bukkit.plugin.java.JavaPlugin;

public final class MCGoldDiggers extends JavaPlugin {

    GoldDiggerWorld world = new GoldDiggerWorld(this);

    @Override
    public void onEnable() {
        getLogger().info("MCGoldDiggers enabled");

        this.world.initialize();

        EventListeners eventsListeners = new EventListeners(this.world);

        // register event listeners
        getServer()
                .getPluginManager()
                .registerEvents(eventsListeners, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MCGoldDiggers disabled");
    }

}
