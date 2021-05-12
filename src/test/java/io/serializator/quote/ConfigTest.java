package io.serializator.quote;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.configuration.MemoryConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class ConfigTest {

    @Test
    @DisplayName("Test if value of proper config key is returned")
    public void testReturnsProperConfigKey() {
        MemoryConfiguration config = new MemoryConfiguration();
        Config out = new Config(config, Logger.getLogger("Quote"));

        config.set("quote-format", "Hello, World");
        assertEquals("Hello, World", out.getQuoteFormat());
    }

    @Test
    @DisplayName("Test if default value is returned if config key does not exist")
    public void testReturnsDefaultIfConfigKeyNotExists() {
        Config out = new Config(new MemoryConfiguration(), Logger.getLogger("Quote"));
        assertEquals(Config.DEFAULT_FORMAT, out.getQuoteFormat());
    }
}
