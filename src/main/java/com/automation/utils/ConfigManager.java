package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Manager to read properties from config.properties file
 */
public class ConfigManager {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fileInput = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fileInput);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties file: " + e.getMessage());
        }
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    /**
     * Get property value with default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get integer property value
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get boolean property value
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    // Commonly used properties
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static int getImplicitWait() {
        return getIntProperty("implicit.wait", 10);
    }

    public static int getExplicitWait() {
        return getIntProperty("explicit.wait", 15);
    }

    public static boolean isHeadless() {
        return getBooleanProperty("headless", false);
    }

    public static String getTestDataPath() {
        return getProperty("test.data.path", "src/test/resources/testdata/");
    }
}
