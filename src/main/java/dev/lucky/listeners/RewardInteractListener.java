package dev.lucky.listeners;

import de.tr7zw.nbtapi.NBTItem;
import dev.lucky.managers.RewardManager;
import dev.lucky.model.Reward;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;


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

        if (!nbtItem.hasNBTData()) return;

        Player player = event.getPlayer();

        PlayerInventory items = rewardManager.getItemsByRewardName(nbtItem.getString("LuckyReward"));
        Reward luckyReward = rewardManager.getRewardByID(nbtItem.getInteger("LuckyReward"));
        items.forEach(itens -> player.getInventory().addItem(itens));
        player.sendMessage("§aAbrindo..");

    }
}