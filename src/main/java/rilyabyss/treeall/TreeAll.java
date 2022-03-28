package rilyabyss.treeall;

import org.bukkit.plugin.java.JavaPlugin;
import rilyabyss.treeall.Listener.BlockBreakListener;

public final class TreeAll extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

    }

    @Override
    public void onDisable() {

    }
}
