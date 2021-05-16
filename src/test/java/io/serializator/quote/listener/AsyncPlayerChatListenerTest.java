package io.serializator.quote.listener;

import io.serializator.quote.Config;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
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

    @Test
    @DisplayName("Test clickable components sent to permissible recipients only")
    public void testClickableComponentsPermissibleRecipients() {

        // mock our sender for the sake of a "NullPointerException"
        Player sender = mock(Player.class);
        when(sender.getDisplayName()).thenReturn("Serializator");

        // mock our recipient
        Player recipient = mock(Player.class);
        when(recipient.hasPermission("quote.quote")).thenReturn(true);
        when(recipient.spigot()).thenReturn(mock(Player.Spigot.class));

        // construct the event ourselves for the sake of a "NullPointerException", AsyncPlayerChatEvent#getMessage() is final and can't be stubbed
        AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(false, sender, "Hello, World", Set.of(recipient));

        out.onAsyncPlayerChat(event);

        // verify that a clickable component was sent
        verify(recipient.spigot()).sendMessage(Mockito.<BaseComponent>any());

        // verify that no clickable component was sent
        when(recipient.hasPermission("quote.quote")).thenReturn(false);
        out.onAsyncPlayerChat(event);
        verify(recipient).sendMessage(Mockito.<String>any());
    }
}
