package dev.lucky.managers;

import dev.lucky.model.Reward;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
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

    private final FileConfiguration fileConfiguration;
    private final ConfigurationSection section = fileConfiguration.getConfigurationSection("rewards");
    private final Map<String, Reward> rewardMap = new HashMap<>();

    private Reward getRewardByName(String name) {
        return rewardMap.get(name);
    }

    public List<ItemStack> getItemsByRewardName(String rewardName) {
        return getRewardByName(rewardName).getItems();
    }

    public List<ItemStack> getItemsOfReward(Reward reward) {
        return reward.getItems();
    }

    public Reward getRewardByID(int id) {
        for (String reward : section.getKeys(false)) {
            if (getRewardByName(reward).getId().equalsIgnoreCase(String.valueOf(id))) return getRewardByName(reward);
        }
        return null;
    }


    public String getRewardList() {
        StringBuilder sb = new StringBuilder();
        for(String rewards : section.getKeys(false)) {
            sb.append(rewards);
        }
        return sb.toString();
    }

    // a gente vai fazer como? inv ou comando pra pegar as recompensas?
    // pode ser inv, no comando abre um inventário, o player taco os intes lá, quando ele fechar a gente pega os itens e seta na reward

    public void loadRewards() {

        for (String key : section.getKeys(true)) {

            String name = fileConfiguration.getString("rewards." + key + ".name");
            int delay = fileConfiguration.getInt("rewards." + key + ".delay");
            Reward reward = new Reward(key, name, delay, getItemsByRewardName(key));

            rewardMap.put(reward.getName(), reward);

        }

    }

}
