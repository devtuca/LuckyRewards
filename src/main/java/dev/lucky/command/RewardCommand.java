package dev.lucky.command;

public class RewardCommand {

    /*private final RewardManager rewardManager;

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

}*/
}
