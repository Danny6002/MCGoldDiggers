package nl.clevernode.mcgolddiggers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MiniGame {

    private final GoldDiggerWorld world;
    private final int miniGameHeight = 100;
    private Boolean isCreated = false;

    MiniGame(GoldDiggerWorld world) {
        this.world = world;
    }

    private World getWorld() {
        return this.world.getWorld();
    }

    private Location getMinigameLocation(int offsetX, int offsetY, int offsetZ) {
        return new Location(
                this.getWorld(),
                offsetX,
                this.world.getGroundLevel() + this.miniGameHeight + offsetY,
                offsetZ
        );
    }

    private void createPlayArea() {
        int fieldSize = 30;
        int offset = fieldSize / 2;
        int wallHeight = 20;

        Material barrierMaterial = Material.BARRIER;

        ArrayList<Material> materials = new ArrayList<>();
        materials.add(Material.GRASS_BLOCK);
        materials.add(Material.QUARTZ_BLOCK);
        materials.add(Material.BAMBOO_BLOCK);
        materials.add(Material.TERRACOTTA);
        materials.add(Material.STONE_BRICKS);
        materials.add(Material.GRAY_CONCRETE);
        materials.add(Material.PURPUR_SLAB);
        materials.add(Material.LECTERN);
        materials.add(Material.BLUE_ICE);
        materials.add(Material.END_STONE);
        materials.add(Material.COBWEB);
        materials.add(Material.COAL_BLOCK);
        materials.add(Material.OBSIDIAN);

        // create outer barrier walls
        for (int k = 0; k < materials.size() + wallHeight; k++) {
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    Block block = this.getMinigameLocation(
                                fieldSize - j - offset - 1,
                                k - materials.size() - (wallHeight / 2),
                                fieldSize - offset
                            ).getBlock();
                    block.setType(barrierMaterial);

                    Block block4 = this.getMinigameLocation(
                            fieldSize - j - offset - 1,
                            k - materials.size() - (wallHeight / 2),
                            (offset * -1) - 1
                    ).getBlock();
                    block4.setType(barrierMaterial);

                    Block block2 = this.getMinigameLocation(
                            fieldSize - offset,
                            k - materials.size() - (wallHeight / 2),
                            fieldSize - j - offset - 1
                    ).getBlock();
                    block2.setType(barrierMaterial);

                    Block block3 = this.getMinigameLocation(
                            (offset * -1) - 1,
                            k - materials.size() - (wallHeight / 2),
                            fieldSize - j - offset - 1
                    ).getBlock();
                    block3.setType(barrierMaterial);
                }
            }
        }

        // create block floors
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                for (int k = 0; k < materials.size(); k++) {
                    Block block = this.getMinigameLocation(j - offset, k * -1, i - offset).getBlock();
                    block.setType(materials.get(k));
                }
            }
        }
    }

    public void teleportToMinigame(Player player) {
        if (!this.isCreated) {
            this.createPlayArea();
        }

        player.teleport(this.getMinigameLocation(0, 1, 0));
    }

}
