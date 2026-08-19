package com.company.library.config;

import com.company.library.exception.ConfigurationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LibraryConfigTest {

    @Test
    @DisplayName("LibraryConfig loads successfully from existing properties")
    void shouldLoadValidConfig() {
        LibraryConfig config = LibraryConfig.INSTANCE;
        assertThat(config.getLibraryName()).isNotBlank();
        assertThat(config.getOpeningHours()).isNotBlank();
        assertThat(config.getFineRate()).isNotNull();
        assertThat(config.getMaxReservations()).isPositive();
    }

    @Test
    @DisplayName("Throws ConfigurationException if required file is missing")
    void shouldThrowWhenFileNotFound() {
        assertThatThrownBy(() -> LibraryConfig.loadProperties("missing-file.properties"))
                .isInstanceOf(ConfigurationException.class)
                .hasMessageContaining("Configuration file not found");
    }

    @Test
    @DisplayName("Throws ConfigurationException if a mandatory key is missing")
    void shouldThrowWhenKeyIsMissing() {
        Properties props = new Properties();
        props.setProperty("library.name", "Kigali City Library");

        assertThatThrownBy(() -> LibraryConfig.getRequiredProperty(props, "library.fine.rate", "test.properties"))
                .isInstanceOf(ConfigurationException.class)
                .hasMessageContaining("Missing mandatory property 'library.fine.rate'");
    }
}