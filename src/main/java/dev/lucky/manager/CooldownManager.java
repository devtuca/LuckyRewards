package dev.lucky.manager;

import com.sun.org.apache.xerces.internal.xs.StringList;
import dev.lucky.model.CooldownFile;
import dev.lucky.model.Reward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Tuca
 * @GitHub: https://github.com/devtuca
 */

@RequiredArgsConstructor
public class CooldownManager {

    @Getter
    private final CooldownFile cooldownFile;
    private final RewardManager rewardManager;


    public ConfigurationSection getMainSection() {
        ConfigurationSection section = cooldownFile.getYamlConfiguration().getConfigurationSection("players");
        if (section == null) {
            cooldownFile.getYamlConfiguration().createSection("players");
            cooldownFile.saveConfig();
        }
        return section;
    }

    public boolean hasUnlockedReward(Player player, Reward reward) {


        long delayToday = getRewardCooldownOfPlayer(player, reward);
        long delayReward = (delayToday + TimeUnit.DAYS.toMillis(reward.getDelay()));
        return delayToday >= delayReward;
    }


    public void addCooldownOfRewardToPlayer(Player player, Reward reward) {

        YamlConfiguration fileConfiguration = cooldownFile.getYamlConfiguration();
        ConfigurationSection section = fileConfiguration.getConfigurationSection("players");
        long days = (System.currentTimeMillis() + TimeUnit.DAYS.toMillis(reward.getDelay()));
        section.set(player.getName() + "." + reward.getName(), days);
        cooldownFile.saveConfig();

    }

    public long getRewardCooldownOfPlayer(Player player, Reward reward) {

        ConfigurationSection section = getMainSection();
        System.out.println(section == null);


        return section.getLong(player.getName() + "." + reward.getName());
    }

    public String getDate(Player player, Reward reward) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(getRewardCooldownOfPlayer(player, reward));
    }

    public void savaAllCooldown() {
        ConfigurationSection section = getMainSection();



        for (String playerName : section.getKeys(false)) {
            for (Reward reward : rewardManager.getAllRewards()) {

                String path = playerName + "." + reward.getName();
                section.set(section.getString(path), section.getLong(path));


                cooldownFile.saveConfig();
            }
        }
    }
}