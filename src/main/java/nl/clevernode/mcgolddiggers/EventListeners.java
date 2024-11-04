package nl.clevernode.mcgolddiggers;

import jdk.tools.jlink.internal.plugins.VendorVersionPlugin;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
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
        Player player = event.getPlayer();
        World world = player.getWorld();

        double chestspawnnumber = Math.random() * 10;
        Integer randomchestnumber = (int) Math.round(chestspawnnumber);
        System.out.println(randomchestnumber);

        double toolrng = Math.random() * 100;
        Integer toolnumber = (int) Math.round(toolrng);

        if (randomchestnumber>8) {
            ArrayList<ItemStack> chestitems = new ArrayList<ItemStack>();

            chestitems.add(new ItemStack(Material.STICK,3));
            chestitems.add(new ItemStack(Material.OAK_LOG,2));

            if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.DIRT) {

                chestitems.add(new ItemStack(Material.OAK_SAPLING));
                chestitems.add(new ItemStack(Material.OAK_SAPLING));
                chestitems.add(new ItemStack(Material.OAK_SAPLING));
                chestitems.add(new ItemStack(Material.OAK_SAPLING));

                if (toolnumber == 1) {
                    ItemStack pickaxe = new ItemStack(Material.GOLDEN_SHOVEL);
                    ItemMeta pickaxemeta = pickaxe.getItemMeta();

                    pickaxemeta.addEnchant(Enchantment.DIG_SPEED,3,true);
                    pickaxemeta.addEnchant(Enchantment.DURABILITY,10,true);
                    pickaxemeta.setDisplayName("Gold Digger");

                    pickaxe.setItemMeta(pickaxemeta);
                    chestitems.add(pickaxe);
                }

            }
            if (block.getType() == Material.STONE) {

                double ironrng = Math.random() * 3;
                Integer ironnumber = (int) Math.round(ironrng);

                chestitems.add(new ItemStack(Material.COBWEB));
                chestitems.add(new ItemStack(Material.COBWEB));
                chestitems.add(new ItemStack(Material.COBWEB));
                chestitems.add(new ItemStack(Material.COBWEB,2));
                chestitems.add(new ItemStack(Material.IRON_INGOT,ironnumber));

                if (toolnumber == 1) {
                    ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
                    ItemMeta pickaxemeta = pickaxe.getItemMeta();

                    pickaxemeta.addEnchant(Enchantment.DIG_SPEED,2,true);
                    pickaxemeta.addEnchant(Enchantment.DURABILITY,10,true);
                    pickaxemeta.setDisplayName("Stone Destroyer");

                    pickaxe.setItemMeta(pickaxemeta);
                    chestitems.add(pickaxe);
                }

            }
            if (block.getType() == Material.DEEPSLATE) {

                double diarng = Math.random() * 2;
                Integer dianumber = (int) Math.round(diarng);

                chestitems.add(new ItemStack(Material.ROTTEN_FLESH,4));
                chestitems.add(new ItemStack(Material.COBWEB,3));
                chestitems.add(new ItemStack(Material.COBWEB,3));
                chestitems.add(new ItemStack(Material.ROTTEN_FLESH, 2));
                chestitems.add(new ItemStack(Material.ROTTEN_FLESH,3));
                chestitems.add(new ItemStack(Material.COBWEB,3));
                chestitems.add(new ItemStack(Material.ROTTEN_FLESH, 2));
                chestitems.add(new ItemStack(Material.DIAMOND, dianumber));
            }
            if (block.getType() == Material.NETHERITE_BLOCK) {

                double diarng = Math.random() * 2;
                Integer dianumber = (int) Math.round(diarng);

                chestitems.add(new ItemStack(Material.MAGMA_BLOCK,4));
                chestitems.add(new ItemStack(Material.MAGMA_CREAM,7));
                chestitems.add(new ItemStack(Material.MAGMA_CREAM,8));
                chestitems.add(new ItemStack(Material.MAGMA_BLOCK, 2));
                chestitems.add(new ItemStack(Material.MAGMA_BLOCK,3));
                chestitems.add(new ItemStack(Material.MAGMA_CREAM,6));
                chestitems.add(new ItemStack(Material.MAGMA_BLOCK, 2));
                chestitems.add(new ItemStack(Material.DIAMOND_BLOCK, dianumber));
                chestitems.add(new ItemStack(Material.DIAMOND_BLOCK, dianumber*dianumber));

                if (toolnumber <= 10) {
                    ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
                    ItemMeta bookmeta = book.getItemMeta();

                    bookmeta.addEnchant(Enchantment.DIG_SPEED,1,true);

                    book.setItemMeta(bookmeta);
                    chestitems.add(book);
                }
            }
            if (block.getType() == Material.OBSIDIAN) {

                double diarng = Math.random() * 2;
                Integer dianumber = (int) Math.round(diarng);

                chestitems.add(new ItemStack(Material.COAL_BLOCK,4));
                chestitems.add(new ItemStack(Material.BLAZE_POWDER,7));
                chestitems.add(new ItemStack(Material.BLAZE_POWDER,8));
                chestitems.add(new ItemStack(Material.COAL_BLOCK, 2));
                chestitems.add(new ItemStack(Material.COAL_BLOCK,3));
                chestitems.add(new ItemStack(Material.BLAZE_POWDER,6));
                chestitems.add(new ItemStack(Material.COAL_BLOCK, 2));
                chestitems.add(new ItemStack(Material.DIAMOND_BLOCK, dianumber*dianumber+3));

                if (toolnumber < 30) {
                    chestitems.add(new ItemStack(Material.SMITHING_TABLE, 1));
                }
                if (toolnumber > 70) {
                    chestitems.add(new ItemStack(Material.ANVIL, 1));
                }
                if (toolnumber <= 10) {
                    chestitems.add(new ItemStack(Material.NETHERITE_INGOT, 1));
                }
            }
            FillChest(block,chestitems);

            event.setCancelled(true);
        }

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
        event.getPlayer().getInventory().clear();
    }

    private void FillChest(Block block, ArrayList<ItemStack> chestitems) {
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();

        Inventory inventory = chest.getBlockInventory();
        for (int i = 0; i < chestitems.size(); i++) {
            boolean isadded = false;
            while (!isadded) {
                double slotspawnnumber = Math.random() * 27;
                Integer randomslotnumber = (int) Math.round(slotspawnnumber);
                if (inventory.getItem(randomslotnumber)==null) {
                    inventory.setItem(randomslotnumber, chestitems.get(i));
                    isadded = true;
                }
            }
        }
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