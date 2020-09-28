package dev.lucky.command;

import dev.lucky.manager.RewardManager;
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
public class RewardCreateCommand implements CommandExecutor {

    private final RewardManager rewardManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) return false;

        if (!player.hasPermission("luckyrewards.rewards.create")) {
            player.sendMessage("§cVocê não tem permissão.");
            return false;
        }

        if (args.length == 4) {
            //recompensas criar [id] [nome] [delay]
            if (args[0].equalsIgnoreCase("criar")) {
                int delay;
                int id;

                try {
                    delay = Integer.parseInt(args[3]);
                    id = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cDigite um número válido de dias!");
                    return false;
                }

                rewardManager.createReward(args[1], id, delay);
                player.sendMessage("§aVocê criou uma recompensa com o nome: §2" + args[1]);
                return false;
            }
        }
        return false;
    }
}
