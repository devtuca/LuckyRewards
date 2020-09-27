package dev.lucky.command;

import com.henryfabio.inventoryapi.enums.InventoryLine;
import dev.lucky.inventory.RewardInventory;
import dev.lucky.managers.RewardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RewardCommand implements CommandExecutor {

    private final RewardManager rewardManager;

    public RewardCommand(RewardManager rewardManager) {
        this.rewardManager = rewardManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if (sender == null) return false;

        if (!player.hasPermission("luckyrewards.rewards.view")) {
            player.sendMessage("§cVocê não tem permissão.");
            return false;
        }

        new RewardInventory(
                "reward.inventory",
                "Menu de Recompensas",
                InventoryLine.SIX,
                rewardManager).openInventory(player);

        return false;
    }

}
