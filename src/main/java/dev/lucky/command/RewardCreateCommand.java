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

        if(args.length != 4) {
            player.sendMessage("/recompensas criar [id] [delay] nome]");
            return false;
        }

        if (args[0].equalsIgnoreCase("criar")) {
            int id;
            long delay;


            System.out.println(args[1]);
            try {
                id = Integer.parseInt(args[1]);
                delay = Long.parseLong(args[2]);

            } catch (NumberFormatException e) {
                player.sendMessage("§cDigite um número válido de dias!");
                return false;
            }

            rewardManager.createReward(args[1], id, delay);
            player.sendMessage("");
            player.sendMessage("§aVocê criou uma recompensa! §2(ID: " + id + "§2)");
            player.sendMessage("§aVocê precisa setar os itens na §2config.yml §aseguindo o template!");
            player.sendMessage("");
            return false;
        }
        return false;
    }
}
