package dev.lucky.command;

import dev.lucky.managers.FileManager;
import dev.lucky.managers.RewardManager;
import dev.lucky.model.Reward;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final FileManager fileManager;

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Reward rewardByID = rewardManager.getRewardByName(args[0]);
        Player player = (Player) sender;

        fileManager.saveConfig();
        player.sendMessage("§aConfigurações reiniciadas com sucesso.");
        return false;
    }
}