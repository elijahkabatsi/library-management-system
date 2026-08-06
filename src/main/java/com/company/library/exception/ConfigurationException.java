package com.company.library.exception;

public class ConfigurationException extends LibraryException {
    private final String propertyKey;

    public ConfigurationException(String pMessage, String pPropertyKey) {
        super(pMessage + " [Key: " + pPropertyKey + "]");
        this.propertyKey = pPropertyKey;
    }

    public ConfigurationException(String pMessage, String pPropertyKey, Throwable pCause) {
        super(pMessage + " [Key: " + pPropertyKey + "]", pCause);
        this.propertyKey = pPropertyKey;
    }

    public String getPropertyKey() {
        return propertyKey;
    }
}