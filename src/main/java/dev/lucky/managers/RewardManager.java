package dev.lucky.managers;

import dev.lucky.model.Reward;
import dev.lucky.serializers.InventorySerializer;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */

@RequiredArgsConstructor
public class RewardManager {

    private final FileManager fileManager;
    private final ConfigurationSection section = fileManager.getMainSection().getConfigurationSection("rewards");
    private final Map<String, Reward> rewardMap = new HashMap<>();
    private final InventorySerializer inventorySerializer;

    public void createReward(String id, String name, int delay, PlayerInventory inventory) {
        section.set(name + ".id", id);
        section.set(name + ".id", id);
        section.set(name + ".delay", delay);
        section.set(name + ".serialized", inventorySerializer.serialize(inventory));
    }

    private Reward getRewardByName(String name) {
        return rewardMap.get(name);
    }

    public ItemStack[] getItemsByRewardName(String rewardName) {
        return getRewardByName(rewardName).getItems();
    }

    public Reward getRewardByID(int id) {
        for (String reward : section.getKeys(false)) {
            if (getRewardByName(reward).getId() == id) return getRewardByName(reward);
        }
        return null;
    }

    public boolean rewardUnlocked(Reward reward) {
        if ()
    }

    public String getRewardList() {
        StringBuilder sb = new StringBuilder();
        for (String rewards : section.getKeys(false)) {
            sb.append(rewards);
        }
        return sb.toString();
    }

    public void loadRewards() throws IOException {

        for (String key : fileManager.getMainSection().getKeys(true)) {

            int id = section.getInt(key + ".id");
            String name = section.getString(key + ".name");
            int delay = section.getInt(key + ".delay");
            ItemStack[] inventory = inventorySerializer.deserializeFromBase64(section.getString(key + "serialized")).getContents();

            Reward reward = new Reward(id, name, delay, inventory);

            rewardMap.put(key, reward);

        }
    }

    public void saveDelay() {
        for (String key : section.getKeys(true)) {
            int delay = section.getInt(key + ".delay");
            section.set(key + ".delay", delay);
        }
    }
}
