package dev.lucky.command;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */

public class RewardCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        ItemStack itemInHand = p.getItemInHand();
        NBTItem nbti = new NBTItem(itemInHand);
        nbti.setString("tucaGato", "sim");

        return false;
    }
}
