package dev.lucky.command;

import dev.lucky.managers.RewardManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                    id = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cDigite um número válido de dias!");
                    return false;
                }

                List<ItemStack> lista = new ArrayList<>(Arrays.asList(player.getInventory().getContents()));
                rewardManager.createReward(id, args[2], delay, lista);
                player.sendMessage("§aVocê criou uma recompensa com o nome: §2" + args[2]);
                return false;
            }
        }
        return false;
    }
}
