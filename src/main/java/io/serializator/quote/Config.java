package io.serializator.quote;

/**
 * A wrapper for easy access and validation of the configuration
 *
 * @since 2.0.0
 */
public class Config {
    private final Quote plugin;

    public Config(Quote plugin) {
        this.plugin = plugin;
    }

    /**
     * Get the format in which a quoted message is to be formatted
     *
     * @return the format in which a quote message is to be formatted
     */
    public final String getQuoteFormat() {
        String format = plugin.getConfig().getString("quote-format", null);

        if (format == null) {
            format = "\"%message%\" ";
            plugin.getLogger().warning("\"quote-format\" is not configured, using the default quote format");
        }

        return format;
    }
}
