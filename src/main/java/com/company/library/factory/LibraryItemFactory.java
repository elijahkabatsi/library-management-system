package com.company.library.factory;

import com.company.library.domain.*;
import com.company.library.exception.UnsupportedItemTypeException;
import com.company.library.exception.ValidationException;

import java.util.Arrays;
import java.util.Map;

public class LibraryItemFactory {

    public static LibraryItem createItem(ItemType type, Map<String, String> attributes) {
        if (type == null) {
            throw new ValidationException("ItemType must not be null");
        }
        if (attributes == null) {
            throw new ValidationException("Attributes map must not be null");
        }

        try {
            return switch (type) {
                case BOOK -> createBook(attributes);
                case MAGAZINE -> createMagazine(attributes);
                case DVD -> createDvd(attributes);
            };
        } catch (NumberFormatException e) {
            throw new ValidationException("Failed to parse numeric attribute in attributes: " + attributes, e);
        }
    }

    public static LibraryItem createItem(String typeStr, Map<String, String> attributes) {
        if (typeStr == null || typeStr.isBlank()) {
            throw new ValidationException("Item type string must not be blank");
        }

        try {
            ItemType type = ItemType.valueOf(typeStr.toUpperCase().trim());
            return createItem(type, attributes);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedItemTypeException(
                    "Unsupported item type: '" + typeStr + "'. Supported types are: " + Arrays.toString(ItemType.values())
            );
        }
    }

    private static Book createBook(Map<String, String> attrs) {
        requireKeys(attrs, "id", "title", "author", "isbn", "publicationYear", "pageCount");
        return new Book.Builder()
                .id(attrs.get("id"))
                .title(attrs.get("title"))
                .author(attrs.get("author"))
                .isbn(attrs.get("isbn"))
                .publicationYear(Integer.parseInt(attrs.get("publicationYear")))
                .pageCount(Integer.parseInt(attrs.get("pageCount")))
                .build();
    }

    private static Magazine createMagazine(Map<String, String> attrs) {
        requireKeys(attrs, "id", "title", "publicationYear", "issueNumber", "publicationMonth");
        return new Magazine(
                attrs.get("id"),
                attrs.get("title"),
                Integer.parseInt(attrs.get("publicationYear")),
                true,
                Integer.parseInt(attrs.get("issueNumber")),
                attrs.get("publicationMonth")
        );
    }

    private static Dvd createDvd(Map<String, String> attrs) {
        requireKeys(attrs, "id", "title", "publicationYear", "durationMinutes", "director");
        return new Dvd(
                attrs.get("id"),
                attrs.get("title"),
                Integer.parseInt(attrs.get("publicationYear")),
                true,
                Integer.parseInt(attrs.get("durationMinutes")),
                attrs.get("director")
        );
    }

    private static void requireKeys(Map<String, String> attrs, String... keys) {
        for (String key : keys) {
            if (!attrs.containsKey(key) || attrs.get(key).isBlank()) {
                throw new ValidationException("Missing required attribute: " + key);
            }
        }
    }
}