package dev.lucky;

import org.bukkit.plugin.java.JavaPlugin;

public final class LuckyRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
