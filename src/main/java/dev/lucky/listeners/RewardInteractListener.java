package dev.lucky.listeners;

import dev.lucky.serializers.InventorySerializer;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */
@RequiredArgsConstructor
public class RewardInteractListener implements Listener {

    private final InventorySerializer inventorySerializer;

    //consegue fazer a parte de verificar qnd for uma recompensa?
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
    }
}