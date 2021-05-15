package io.serializator.quote.listener;

import io.serializator.quote.Config;
import org.bukkit.configuration.MemoryConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

public class AsyncPlayerChatListenerTest {
    private AsyncPlayerChatListener out;

    @BeforeEach
    public void setUp() {
        MemoryConfiguration config = new MemoryConfiguration();
        config.set("quote-format", "Hello %message%");
        out = new AsyncPlayerChatListener(new Config(config, Logger.getLogger("Hello, World")));
    }

    @Test
    @DisplayName("Test if quote is formatted correctly with message")
    public void testQuoteFormatFromMessage() {
        assertEquals("Hello World", out.getQuoteFromMessage("World"));
    }
}
