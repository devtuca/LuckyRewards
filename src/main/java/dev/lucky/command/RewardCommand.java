package dev.lucky.command;

import dev.lucky.inventory.RewardInventory;
import dev.lucky.manager.CooldownManager;
import dev.lucky.manager.RewardManager;
import dev.lucky.model.Reward;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class RewardCommand implements CommandExecutor {

    private final RewardManager rewardManager;
    private final CooldownManager cooldownManager;
    private final Plugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;

        if (sender == null) return false;

        if (!player.hasPermission("luckyrewards.rewards.view")) {
            player.sendMessage("§cVocê não tem permissão.");
            return false;
        }

        RewardInventory inventory = new RewardInventory(rewardManager, cooldownManager);
        inventory.openInventory(player);

        return false;
    }

}

