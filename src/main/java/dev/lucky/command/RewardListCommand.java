package dev.lucky.command;

import dev.lucky.managers.RewardManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */
@RequiredArgsConstructor
public class RewardListCommand implements CommandExecutor {

    private final RewardManager rewardManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        p.sendMessage("§aAtualmente temos essas recompensas: " + rewardManager.getRewardList());

        return false;
    }
}
