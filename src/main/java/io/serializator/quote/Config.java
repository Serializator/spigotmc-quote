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

    public final void validate() {
        if (!config.contains("quote-format")) {
            logger.warning("\"quote-format\" is not configured, using the default quote format");
        }
    }

    /**
     * Get the format in which a quoted message is to be formatted
     *
     * @return the format in which a quote message is to be formatted
     */
    public final String getQuoteFormat() {
        return config.getString("quote-format", DEFAULT_FORMAT);
    }
}
