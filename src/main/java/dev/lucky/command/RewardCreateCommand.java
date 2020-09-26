package dev.lucky.command;

import dev.lucky.managers.RewardManager;
import dev.lucky.serializers.InventorySerializer;
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
        Player p = (Player) sender;

        if(sender == null) return false;
        if(!p.hasPermission("luckyrewards.rewards.create")) { p.sendMessage("§cVocê não tem permissão."); return false; }
        if(args.length != 3) p.sendMessage("§cUse: /recompensas criar [id] [nome] [delay] ");

        int days;

        try {
            days = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){
            p.sendMessage("§cDigite um número válido de dias!");
            return false;
        }

        rewardManager.createReward(args[0], args[1], days, p.getInventory());
        p.sendMessage("§aVocê criou uma recompensa com o nome: §2" + args[1]);


        return false;
    }
}
