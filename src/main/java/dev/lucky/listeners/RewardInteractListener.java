package dev.lucky.listeners;

import de.tr7zw.nbtapi.NBTItem;
import dev.lucky.managers.RewardManager;
import dev.lucky.model.Reward;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */
@RequiredArgsConstructor
public class RewardInteractListener implements Listener {

    private final RewardManager rewardManager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        ItemStack item = event.getItem();
        NBTItem nbtItem = new NBTItem(item);

        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().hasDisplayName() && !item.getItemMeta().hasLore()) return;
        if (!nbtItem.hasNBTData()) return;

        Player player = event.getPlayer();

        Reward luckyReward = rewardManager.getRewardByName(nbtItem.getString("LuckyReward"));

        player.getInventory().addItem((ItemStack) luckyReward.getItems());
        player.sendMessage("§aAbrindo recompensa...");

    }
}