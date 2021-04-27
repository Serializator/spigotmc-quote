package io.serializator.quote.listener;

import io.serializator.quote.Config;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Responsible for intercepting the original message and making it clickable
 *
 * @since 2.0.0
 * @see AsyncPlayerChatEvent
 */
public class AsyncPlayerChatListener implements Listener {
    private final Config config;

    public AsyncPlayerChatListener(Config config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        TextComponent quoted = createClickableChatComponent(event.getPlayer(), event.getMessage(), event.getFormat());
        event.getRecipients().forEach(recipient -> recipient.spigot().sendMessage(quoted));
        event.setCancelled(true);
    }

    /**
     * Create a chat component which can be clicked to quote a given message
     *
     * @param player the player who sent the message
     * @param originalMessage the message the player originally sent
     * @param originalFormat the format in which the message would orignally be formatted
     * @return the chat component which can be clicked to quote the given message
     */
    private TextComponent createClickableChatComponent(Player player, String originalMessage, String originalFormat) {
        TextComponent component = new TextComponent(String.format(originalFormat, player.getDisplayName(), originalMessage));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, getQuoteFromMessage(originalMessage)));
        return component;
    }

    /**
     * Format a message according to the configured quote format
     *
     * @param message the message to format as a quote
     * @return the message formatted as a quote
     */
    private String getQuoteFromMessage(String message) {
        String format = config.getQuoteFormat();
        return format.replace("%message%", message);
    }
}
