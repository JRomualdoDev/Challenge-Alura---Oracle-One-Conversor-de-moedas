package br.com.challenge.conversor.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigUtil {

    // Properties is a Java class that serves to store key-value pairs
    // (like a Map), widely used for configuration files.
    private static Properties props = null;

    private ConfigUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    private static Properties getProperties() throws IOException {
        if (props == null) {
            props = new Properties();
            try (FileInputStream fis = new FileInputStream("config.properties")) {
                props.load(fis);
            }
        }
        return props;
    }

    public static String getApiKey() throws IOException {
        return getProperties().getProperty("API_KEY");
    }
}
