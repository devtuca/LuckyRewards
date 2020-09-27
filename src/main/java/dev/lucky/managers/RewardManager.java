package dev.lucky.managers;

import dev.lucky.model.Reward;
import dev.lucky.serializers.InventorySerializer;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */

@RequiredArgsConstructor
public class RewardManager {

    private final FileManager fileManager;// não pode estar inicializada essa variavel, wtf como q n? kk, tenta inicilizar ela no método que precisa
    private final Map<String, Reward> rewardMap = new HashMap<>();
    private final List<ItemStack> rewardItems = new ArrayList<>();
    private final InventorySerializer inventorySerializer;


    public void createReward(int id, String name, int delay, List<ItemStack> itemStacks) {
        ConfigurationSection section = fileManager.getMainSection();

        section.set(name + ".id", id);
        section.set(name + ".delay", delay);
        section.set(name + ".serialized", inventorySerializer.encode((ItemStack) itemStacks));
        fileManager.saveConfig();

        rewardMap.put(name, new Reward(id, name, delay, inventory));
    }

    public Reward getRewardByName(String name) {
        if (rewardMap.containsKey(name)) {
            return rewardMap.get(name);
        }
        ConfigurationSection section = fileManager.getMainSection();
        for (String key : section.getKeys(false)) {
            int id = section.getInt(key + ".id");
            int delay = section.getInt(key + ".delay");
            Inventory items = inventorySerializer.decode(key + ".serialized");
            return new Reward(id, key, delay, items);
        }
        return null;
    }

    public String getRewardList() {
        ConfigurationSection section = fileManager.getMainSection();
        StringBuilder sb = new StringBuilder();
        for (String rewards : section.getKeys(false)) {
            sb.append(rewards);
        }
        return sb.toString();
    }

    public void loadRewards() {
        ConfigurationSection section = fileManager.getMainSection();
        for (String key : section.getKeys(false)) {
            Reward reward = getRewardByName(key);
            rewardMap.put(key, reward);
        }
    }

    public void saveDelay() {
        ConfigurationSection section = fileManager.getMainSection();
        for (String key : section.getKeys(true)) {
            int delay = section.getInt(key + ".delay");
            section.set(key + ".delay", delay);
        }
    }
}
