package dev.lucky.manager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * @author Tuca
 * @Github: https://github.com/devtuca
 */

@RequiredArgsConstructor
public class FileManager {

    private final Plugin plugin;
    @Getter
    private final FileConfiguration fileConfiguration;

    public void saveConfig() {
        plugin.saveConfig();
    }

    public void checkSections() {
        if(getMainSection() == null) {
            fileConfiguration.createSection("rewards");
            plugin.saveConfig();
        }
    }
    public void sendQuery(String path, Object value) {
        plugin.getConfig().set(path, value);
    }

    public ConfigurationSection getMainSection() {
        ConfigurationSection section = fileConfiguration.getConfigurationSection("rewards");
        if (section == null) fileConfiguration.createSection("rewards");
        return section;
    }
}
