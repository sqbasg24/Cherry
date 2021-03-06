package club.rigox.cherry.utils;

import club.rigox.cherry.Cherry;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static club.rigox.cherry.utils.Logger.error;
import static club.rigox.cherry.utils.Logger.warn;

public class Config {
    private final Cherry cherry;

    public Config(Cherry plugin) {
        this.cherry = plugin;
    }

    public FileConfiguration createConfig(String configName) {
        File configFile = new File(cherry.getDataFolder(), configName + ".yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            cherry.saveResource(configName + ".yml", false);
        }

        FileConfiguration cfg = new YamlConfiguration();
        try {
            cfg.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            warn(String.format("A error occurred while copying the config %s.yml to the plugin data folder. Error: %s", configName, e));
            e.printStackTrace();
        }
        return cfg;
    }

    public static String getLangString(String path) {
        if (Cherry.instance.getLang().getString(path) == null) {
            error(String.format("The folowing path doesn't exists on Lang.yml! %s, please add it.", path));
            return "&cPlease inform this error to an administrator and let it know to review the console.";
        }
        return Cherry.instance.getLang().getString(Objects.requireNonNull(path));
    }
}
