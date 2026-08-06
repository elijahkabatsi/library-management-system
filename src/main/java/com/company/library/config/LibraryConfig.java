package com.company.library.config;

import com.company.library.exception.ConfigurationException;
import java.io.InputStream;
import java.util.Properties;

public enum LibraryConfig {
    INSTANCE;

    private String libraryName;
    private String openingHours;
    private double fineRate;
    private int maxReservations;

    LibraryConfig() {
        loadConfiguration("application.properties");
    }

    private void loadConfiguration(String pResourcePath) {
        try (InputStream inputFolder = getClass().getClassLoader().getResourceAsStream(pResourcePath)) {
            if (inputFolder == null) {
                throw new ConfigurationException("Configuration file missing from classpath", pResourcePath);
            }

            Properties parsedProps = new Properties();
            parsedProps.load(inputFolder);

            libraryName = getMandatoryProperty(parsedProps, "app.library.name");
            openingHours = getMandatoryProperty(parsedProps, "app.library.openingHours");
            fineRate = Double.parseDouble(getMandatoryProperty(parsedProps, "app.library.fineRate"));
            maxReservations = Integer.parseInt(getMandatoryProperty(parsedProps, "app.library.maxReservations"));

        } catch (ConfigurationException configErr) {
            throw configErr;
        } catch (Exception genericErr) {
            throw new ConfigurationException("Failed to process properties file", pResourcePath, genericErr);
        }
    }

    private String getMandatoryProperty(Properties pProperties, String pConfigKey) {
        String fetchedValue = pProperties.getProperty(pConfigKey);
        if (fetchedValue == null || fetchedValue.isBlank()) {
            throw new ConfigurationException("Missing mandatory configuration key", pConfigKey);
        }
        return fetchedValue.trim();
    }

    public String getLibraryName() { return libraryName; }
    public String getOpeningHours() { return openingHours; }
    public double getFineRate() { return fineRate; }
    public int getMaxReservations() { return maxReservations; }
}