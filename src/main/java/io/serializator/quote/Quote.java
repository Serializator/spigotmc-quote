package io.serializator.quote;

import io.serializator.quote.listener.AsyncPlayerChatListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * A plugin which allows players to "quote" others in chat by clicking on their message
 *
 * @since 2.0.0
 */
public class Quote extends JavaPlugin {

    @Override
    public void onEnable() {
        Config config = initializeConfig();
        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(config), this);
    }

    /**
     * Save the default config and instantiate our wrapper
     *
     * @return our wrapper for easy access to the configuration
     */
    private Config initializeConfig() {
        saveDefaultConfig();
        Config config = new Config(YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml")), getLogger());
        config.validate();
        return config;
    }
}
