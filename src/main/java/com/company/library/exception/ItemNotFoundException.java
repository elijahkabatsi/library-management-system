// File: src/main/java/com/company/library/exception/ItemNotFoundException.java
package com.company.library.exception;

public class ItemNotFoundException extends LibraryException {
    private final String itemId;

    public ItemNotFoundException(String pMessage, String pItemId) {
        super(pMessage + " [Key/ID: " + pItemId + "]");
        this.itemId = pItemId;
    }

    public String getItemId() {
        return itemId;
    }
}