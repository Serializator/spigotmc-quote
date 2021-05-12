package io.serializator.quote;

import org.bukkit.configuration.Configuration;

import java.util.logging.Logger;

/**
 * A wrapper for easy access and validation of the configuration
 *
 * @since 2.0.0
 */
public class Config {
    public static final String DEFAULT_FORMAT = "\"%message%\" ";

    private final Configuration config;
    private final Logger logger;

    public Config(Configuration config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    /**
     * Get the format in which a quoted message is to be formatted
     *
     * @return the format in which a quote message is to be formatted
     */
    public final String getQuoteFormat() {
        String format = config.getString("quote-format", null);

        if (format == null) {
            format = DEFAULT_FORMAT;
            logger.warning("\"quote-format\" is not configured, using the default quote format");
        }

        return format;
    }
}
