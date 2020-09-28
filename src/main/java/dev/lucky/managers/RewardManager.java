package dev.lucky.managers;

import dev.lucky.model.Reward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */

@RequiredArgsConstructor
public class RewardManager {

    private final FileManager fileManager;
    @Getter
    private final Map<String, Reward> rewardMap = new HashMap<>();
    short data;
    int amount;
    private Material material;

    public void createReward(String rewardName, int id, int delay) {
        ConfigurationSection section = fileManager.getMainSection();
        section.set(rewardName + ".id", id);
        section.set(rewardName + ".delay", delay);
        section.set(rewardName + ".items", null);
        fileManager.saveConfig();
    }

    public Reward getRewardByName(String value) {
        if (rewardMap.containsKey(value)) {
            return rewardMap.get(value);
        }
        loadRewards();
        return rewardMap.get(value);
    }

    public Reward getRewardByID(int id) {
        ConfigurationSection section = fileManager.getMainSection();
        for (String rewardName : section.getKeys(false)) {
            int delay = section.getInt(rewardName + ".delay");
            int rewardID = section.getInt(rewardName + ".id");
            Reward reward = new Reward(rewardName, rewardID, delay);
            if (reward.getId() == id) return reward;
        }
        return null;
    }

    public void loadRewards() {


        ConfigurationSection section = fileManager.getMainSection();

        for (String rewardName : section.getKeys(false)) {

            int delay = section.getInt(rewardName + ".delay");
            int id = section.getInt(rewardName + ".id");

            List<ItemStack> itens = new ArrayList<>();

            Material material;
            short data;
            int amount;

            for (String materialID : section.getStringList(rewardName + ".items")) {


                material = Material.getMaterial(Integer.parseInt(materialID.split(",")[0]));
                data = Short.parseShort(materialID.split(",")[1]);
                amount = Integer.parseInt(materialID.split(",")[2]);

                ItemStack itemStack = new ItemStack(material, amount, data);
                itens.add(itemStack);
                Reward reward = new Reward(rewardName, id, delay);
                rewardMap.put(rewardName, reward);
            }
        }
    }
}