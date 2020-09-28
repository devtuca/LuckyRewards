package dev.lucky;

import com.henryfabio.inventoryapi.manager.InventoryManager;
import dev.lucky.command.RewardCommand;
import dev.lucky.command.RewardCreateCommand;
import dev.lucky.manager.FileManager;
import dev.lucky.manager.RewardManager;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckyRewards extends JavaPlugin {


    @SneakyThrows
    @Override
    public void onEnable() {
        saveDefaultConfig();

        FileManager fileManager = new FileManager(this, getConfig());
        RewardManager rewardManager = new RewardManager(fileManager);

        fileManager.checkSections();

        rewardManager.loadRewards();

        command("inv", new RewardCommand(rewardManager));
        command("recompensas", new RewardCreateCommand(rewardManager));
        InventoryManager.enable(this);

    }


    @Override
    public void onDisable() {
    }

    public void event(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void command(String command, CommandExecutor commandExecutor) {
        getCommand(command).setExecutor(commandExecutor);
    }
}