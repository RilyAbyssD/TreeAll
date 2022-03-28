package rilyabyss.treeall.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        // 壊したブロックの座標を取得
        Location loc = event.getBlock().getLocation();

        // 壊したブロックの最高の高さを取得
        int block = loc.getWorld().getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ());

        if (isAxe(p.getItemInHand().getType())) {
            if (loc.getBlockY() != block) {
                breakTree(loc, block, event.getBlock().getType());
            } else {
            }
        } else {
        }


    }

    public void breakTree(Location loc, int HighestBlock, Material material) {

        ArrayList<Block> blockList = new ArrayList<>();

        for (int i = loc.getBlockY(); i < HighestBlock; i++) {
            loc = loc.add(0, 1, 0);

            if (isTree(loc.getBlock().getType())) {
                blockList.add(loc.getBlock());
            } else {
                break;
            }
            loc.getBlock().breakNaturally(new ItemStack(Material.DIAMOND_AXE));

        }

    }


    // 伐採対応アイテム識別
    private boolean isAxe(Material material) {

        switch (material) {
            case DIAMOND_AXE:
            case IRON_AXE:
            case GOLDEN_AXE:
            case STONE_AXE:
            case WOODEN_AXE:
                return true;
        }
        return false;

    }

    // 伐採対応木識別
    private boolean isTree(Material material) {

        switch (material) {
            case ACACIA_LOG:
            case BIRCH_LOG:
            case DARK_OAK_LOG:
            case JUNGLE_LOG:
            case OAK_LOG:
            case SPRUCE_LOG:
                return true;
        }
        return false;

    }

}
