package zm.co.gsb.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
                logger.info("config.properties file loaded successfully");
            } else {
                logger.error("Could not find config.properties");
                throw new RuntimeException("Could not find find config.properties");
            }
        } catch (IOException e) {
            logger.error("Could not load configuration", e);
            throw new RuntimeException("Could not load configuration", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property: {} is not in config.properties file", key);
        }
        return value;
    }
}


