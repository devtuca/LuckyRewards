package dev.lucky.manager;

import dev.lucky.model.Reward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.Element;
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
    private final List<ItemStack> itens = new ArrayList<>();

    public void createReward(String rewardName, int id, long delay) {

        ConfigurationSection section = fileManager.getMainSection();
        section.set(rewardName + ".id", id);
        section.set(rewardName + ".delay", delay);
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

    public List<Reward> getAllRewards() {
        ConfigurationSection section = fileManager.getMainSection();

            List<Reward> rewards = new ArrayList<>();
            section.getKeys(false).forEach(rewardName -> rewards.add(getRewardByName(rewardName)));

        return rewards;
    }

    public void loadRewards() {


        ConfigurationSection section = fileManager.getMainSection();

        for (String rewardName : section.getKeys(false)) {

            int delay = section.getInt(rewardName + ".delay");
            int id = section.getInt(rewardName + ".id");

            for (String materialID : section.getStringList(rewardName + ".items")) {

                Material material = Material.getMaterial(Integer.parseInt(materialID.split(",")[0]));

                short data = Short.parseShort(materialID.split(",")[1]);
                int amount = Integer.parseInt(materialID.split(",")[2]);

                ItemStack itemStack = new ItemStack(material, amount, data);
                itens.add(itemStack);
                Reward reward = new Reward(rewardName, id, delay);
                reward.setItems(itens);
                rewardMap.put(rewardName, reward);
            }
        }
    }
}