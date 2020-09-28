package dev.lucky;

import dev.lucky.command.RewardCreateCommand;
import dev.lucky.command.Test;
import dev.lucky.listeners.RewardInteractListener;
import dev.lucky.managers.FileManager;
import dev.lucky.managers.RewardManager;
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

        command("testar", new Test(rewardManager, fileManager));
        command("recompensas", new RewardCreateCommand(rewardManager));
        event(new RewardInteractListener(rewardManager));
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