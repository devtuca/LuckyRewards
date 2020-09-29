package dev.lucky.model;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * @author: Tuca
 * @GitHub: https://github.com/devtuca
 */
public class CooldownFile {

    @Getter
    private final File file = new File("plugins/LuckyRewards/players-delay.yml");
    @Getter
    private final YamlConfiguration yamlConfiguration;


    @SneakyThrows
    public CooldownFile(Plugin plugin) {
        if (!(file.exists())) { plugin.saveResource("players-delay.yml", false); }
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    @SneakyThrows
    public void saveConfig() {
        yamlConfiguration.save(this.file);
    }
}
