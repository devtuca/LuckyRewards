package dev.lucky.command;

import dev.lucky.managers.RewardManager;
import dev.lucky.model.Reward;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author: Tuca
 * @GitHub: https://github.com/devtuca
 */
@RequiredArgsConstructor
public class Test implements CommandExecutor {

    private final RewardManager rewardManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Reward rewardByID = rewardManager.getRewardByName(args[0]);
        Player player = (Player) sender;

        rewardByID.getItems().forEach(items -> player.getInventory().addItem(items));
        return false;
    }
}