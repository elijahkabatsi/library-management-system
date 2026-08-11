// File: src/main/java/com/company/library/util/PrototypeRegistry.java
package com.company.library.util;

import com.company.library.domain.LibraryItem;
import com.company.library.exception.ItemNotFoundException;
import com.company.library.exception.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry {
    private final Map<String, LibraryItem> registryMap = new HashMap<>();

    public void registerTemplate(String pTemplateKey, LibraryItem pSourceItem) {
        if (pTemplateKey == null || pTemplateKey.isBlank()) {
            throw new ValidationException("Template key must not be null or blank", "pTemplateKey", pTemplateKey);
        }
        if (pSourceItem == null) {
            throw new ValidationException("Source template item must not be null", "pSourceItem", null);
        }
        if (registryMap.containsKey(pTemplateKey)) {
            throw new IllegalStateException("Duplicate template key registration: " + pTemplateKey);
        }
        registryMap.put(pTemplateKey, pSourceItem);
    }

    public LibraryItem get(String pTemplateKey) {
        LibraryItem prototype = registryMap.get(pTemplateKey);
        if (prototype == null) {
            throw new ItemNotFoundException("Template key not found in prototype registry", pTemplateKey);
        }
        return prototype.copy();
    }
}