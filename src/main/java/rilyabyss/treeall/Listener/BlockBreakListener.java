package rilyabyss.treeall.Listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        // クリエイティブモードだったら
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }

        // 壊したブロックの座標を取得
        Location loc = event.getBlock().getLocation();

        // 壊したブロックの最高の高さを取得
        int block = loc.getWorld().getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ());

        if (isAxe(p.getItemInHand().getType()))
            if (loc.getBlockY() != block)
                breakTree(loc, block);
    }

    public void breakTree(Location loc, int HighestBlock) {

        for (int i = loc.getBlockY(); i < HighestBlock; i++) {

            loc = loc.add(0, 1, 0);

            // ブロックが木ではなかったら
            if (!isTree(loc.getBlock().getType()))
                break;
            loc.getBlock().breakNaturally();

            treeXPos(loc);
        }

    }

    public void treeXPos(Location loc) {

        // X座標
        for (int x = 1; x < 4; x++) {
            for (int xl = 1; xl < 4; xl++) {
                // Z座標
                for (int z = 1; z < 4; z++) {
                    for (int lz = 1; lz < 4; lz++) {
                        loc = loc.add(0, 0, -lz);
                        // ブロックが木ではなかったら
                        if (!isTree(loc.getBlock().getType())) {
                            loc = loc.add(0, 0, lz);
                            break;
                        }
                        loc.getBlock().breakNaturally();
                        loc = loc.add(0, 0, lz);
                    }
                    loc = loc.add(0, 0, z);
                    // ブロックが木ではなかったら
                    if (!isTree(loc.getBlock().getType())) {
                        loc = loc.add(0, 0, -z);
                        break;
                    }
                    loc.getBlock().breakNaturally();
                    loc = loc.add(0, 0, -z);
                }
                loc = loc.add(-xl, 0, 0);
                // ブロックが木ではなかったら
                if (!isTree(loc.getBlock().getType())) {
                    loc = loc.add(xl, 0, 0);
                    break;
                }
                loc.getBlock().breakNaturally();
                loc = loc.add(xl, 0, 0);
            }
            loc = loc.add(x, 0, 0);
            // ブロックが木ではなかったら
            if (!isTree(loc.getBlock().getType())) {
                loc = loc.add(-x, 0, 0);
                break;
            }
            loc.getBlock().breakNaturally();
            loc = loc.add(-x, 0, 0);
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
