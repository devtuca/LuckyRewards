package dev.lucky.inventory;

import com.henryfabio.inventoryapi.editor.InventoryEditor;
import com.henryfabio.inventoryapi.enums.InventoryLine;
import com.henryfabio.inventoryapi.inventory.global.GlobalInventory;
import com.henryfabio.inventoryapi.inventory.paged.PagedInventory;
import com.henryfabio.inventoryapi.item.InventoryItem;
import com.henryfabio.inventoryapi.viewer.paged.PagedViewer;
import de.tr7zw.nbtapi.NBTItem;
import dev.lucky.managers.RewardManager;
import dev.lucky.model.Reward;
import dev.lucky.utils.ItemComposer;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RewardInventory extends PagedInventory {

    private final RewardManager rewardManager;

    public RewardInventory(String identifier, String title, InventoryLine line, RewardManager rewardManager) {
        super(identifier, title, line);
        this.rewardManager = rewardManager;
    }

    @Override
    protected void onCreate(PagedViewer viewer) {
        super.onCreate(viewer);
    }

    @Override
    protected void onOpen(PagedViewer pagedViewer, InventoryEditor inventoryEditor) {
        //
    }

    @Override
    protected void onUpdate(PagedViewer pagedViewer, InventoryEditor inventoryEditor) {
        //
    }

    @Override
    public List<InventoryItem> getPagesItems(PagedViewer pagedViewer) {


        List<InventoryItem> itemList = new LinkedList<>();
        Collection<Reward> rewardCollection = rewardManager.getRewardMap().values();

        for (Reward reward : rewardCollection) {

            NBTItem nbtItem = new NBTItem(new ItemComposer(Material.STORAGE_MINECART)
                    .setName("§a" + reward.getName())
                    .toItemStack());

            nbtItem.setString("LuckyRewards", reward.getId() + "");

            itemList.add(new InventoryItem(nbtItem.getItem())

                    .addCallback(
                            ClickType.LEFT,
                            click -> {
                                List<ItemStack> items = rewardManager.getRewardByName(reward.getName()).getItems();
                                for (ItemStack content : items) {
                                    click.getPlayer().getInventory().addItem(content);
                                }
                                click.getPlayer().sendMessage("§aVocê coletou a recompensa §f" + reward.getName());
                            }
                    )
                    .addCallback(
                            ClickType.RIGHT,
                            click -> {

                                new GlobalInventory("rewards.rewardView", reward.getName(), InventoryLine.SIX) {

                                    @Override
                                    protected void onCreate(InventoryEditor inventoryEditor) {
                                        List<ItemStack> items = rewardManager.getRewardByName(reward.getName()).getItems();

                                        AtomicInteger slot = new AtomicInteger();

                                        items.forEach(itemStack -> {

                                            inventoryEditor.setItem(slot.get(), new InventoryItem(itemStack)
                                                    .addDefaultCallback(event -> {
                                                        event.setCancelled(true);
                                                    }));
                                            slot.getAndIncrement();

                                        });
                                    }

                                    @Override
                                    protected void onUpdate(InventoryEditor inventoryEditor) {

                                    }
                                }.openInventory(click.getPlayer());

                            }
                    )

            );

        }

        return itemList;
    }

}
