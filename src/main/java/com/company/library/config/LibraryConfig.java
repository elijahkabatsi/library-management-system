package com.company.library.config;

import com.company.library.exception.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

public enum LibraryConfig {
    INSTANCE;

    private final String libraryName;
    private final String openingHours;
    private final BigDecimal fineRate;
    private final int maxReservations;

    LibraryConfig() {
        String configFile = "application.properties";
        Properties props = loadProperties(configFile);

        this.libraryName = getRequiredProperty(props, "library.name", configFile);
        this.openingHours = getRequiredProperty(props, "library.opening.hours", configFile);

        try {
            this.fineRate = new BigDecimal(getRequiredProperty(props, "library.fine.rate", configFile));
        } catch (NumberFormatException e) {
            throw new ConfigurationException("Invalid number format for 'library.fine.rate' in " + configFile, e);
        }

        try {
            this.maxReservations = Integer.parseInt(getRequiredProperty(props, "library.max.reservations", configFile));
        } catch (NumberFormatException e) {
            throw new ConfigurationException("Invalid integer format for 'library.max.reservations' in " + configFile, e);
        }
    }

    public static Properties loadProperties(String filePath) {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = LibraryConfig.class.getClassLoader();
        }

        try (InputStream input = LibraryConfig.class.getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new ConfigurationException("Configuration file not found: " + filePath);
            }
            props.load(input);
            return props;
        } catch (IOException e) {
            throw new ConfigurationException("Failed to read configuration file: " + filePath, e);
        }
    }

    public static String getRequiredProperty(Properties props, String key, String filePath) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new ConfigurationException("Missing mandatory property '" + key + "' in " + filePath);
        }
        return value.trim();
    }

    public String getLibraryName() { return libraryName; }
    public String getOpeningHours() { return openingHours; }
    public BigDecimal getFineRate() { return fineRate; }
    public int getMaxReservations() { return maxReservations; }
}