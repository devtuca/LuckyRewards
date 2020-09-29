package dev.lucky.inventory;

import com.henryfabio.inventoryapi.editor.InventoryEditor;
import com.henryfabio.inventoryapi.enums.InventoryLine;
import com.henryfabio.inventoryapi.inventory.global.GlobalInventory;
import com.henryfabio.inventoryapi.inventory.paged.PagedInventory;
import com.henryfabio.inventoryapi.item.InventoryItem;
import com.henryfabio.inventoryapi.viewer.paged.PagedViewer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.lucky.manager.CooldownManager;
import dev.lucky.manager.RewardManager;
import dev.lucky.model.CooldownFile;
import dev.lucky.model.Reward;
import dev.lucky.util.ItemComposer;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class RewardInventory extends PagedInventory {

    private final RewardManager rewardManager;
    private final CooldownManager cooldownManager;

    public RewardInventory(RewardManager rewardManager, CooldownManager cooldownManager) {
        super("reward.inventory", "§aMenu de Recompensas", InventoryLine.SIX);
        this.rewardManager = rewardManager;
        this.cooldownManager = cooldownManager;
    }


    @Override
    protected void onOpen(PagedViewer pagedViewer, InventoryEditor inventoryEditor) {
    }

    @Override
    protected void onUpdate(PagedViewer pagedViewer, InventoryEditor inventoryEditor) {
    }

    @Override
    public List<InventoryItem> getPagesItems(PagedViewer pagedViewer) {


        List<InventoryItem> itemList = new LinkedList<>();
        Collection<Reward> rewardCollection = rewardManager.getRewardMap().values();

        for (Reward reward : rewardCollection) {

            NBTItem nbtItem = new NBTItem(new ItemComposer(Material.STORAGE_MINECART)
                    .setName("§a" + reward.getName())
                    .setLore(
                            "",
                            "§7Clique com o botão §e§lESQUERDO §7para pegar as recompensas.",
                            "§7Clique com o botão §e§lDIREITO §7para ver as recompensas",
                            ""
                    )
                    .toItemStack());

            nbtItem.setString("LuckyRewards", reward.getId() + "");

            itemList.add(new InventoryItem(nbtItem.getItem())

                    .addCallback(
                            ClickType.LEFT,
                            click -> {

                                Reward rewardByName = rewardManager.getRewardByName(nbtItem.getString("LuckyRewards"));
                                boolean hasUnlocked = cooldownManager.hasUnlockedReward(click.getPlayer(), rewardByName);

                                if (hasUnlocked) {
                                    List<ItemStack> items = rewardByName.getItems();
                                    for (ItemStack item : items) {
                                        click.getPlayer().getInventory().addItem(item);
                                    }
                                    click.getPlayer().sendMessage("§aAs recompensas foram adicionados em seu inventário!");
                                } else {
                                    click.getPlayer().sendMessage("§cVocê só poderá pegar em: " + cooldownManager.getDate(click.getPlayer(), rewardByName));
                                    click.getPlayer().closeInventory();
                                }

                            }
                    )
                    .addCallback(
                            ClickType.RIGHT,
                            click -> {

                                new GlobalInventory("rewards.rewardView", reward.getName(), InventoryLine.SIX) {

                                    @Override
                                    protected void onCreate(InventoryEditor inventoryEditor) {
                                        List<ItemStack> items = rewardManager.getRewardByName(reward.getName()).getItems();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
