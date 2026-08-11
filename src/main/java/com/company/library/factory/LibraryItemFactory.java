package com.company.library.factory;

import com.company.library.domain.Book;
import com.company.library.domain.Dvd;
import com.company.library.domain.LibraryItem;
import com.company.library.domain.Magazine;
import com.company.library.exception.ValidationException;
import com.company.library.util.IdGenerator;

import java.util.Collections;
import java.util.Map;

public class LibraryItemFactory {

    public static LibraryItem createItem(ItemType pType, Map<String, String> pAttributes) {
        if (pType == null) {
            throw new ValidationException("ItemType cannot be null", "pType", null);
        }
        if (pAttributes == null) {
            throw new ValidationException("Attributes map cannot be null", "pAttributes", null);
        }

        String title = pAttributes.get("title");
        if (title == null || title.isBlank()) {
            throw new ValidationException("Title is required", "title", title);
        }

        int year;
        try {
            year = Integer.parseInt(pAttributes.getOrDefault("publicationYear", "2026"));
        } catch (NumberFormatException err) {
            throw new ValidationException("Invalid publication year", "publicationYear", pAttributes.get("publicationYear"));
        }

        return switch (pType) {
            case BOOK -> new Book.Builder()
                    .id(IdGenerator.INSTANCE.nextBookId())
                    .title(title)
                    .author(pAttributes.get("author"))
                    .isbn(pAttributes.get("isbn"))
                    .publicationYear(year)
                    .pageCount(Integer.parseInt(pAttributes.getOrDefault("pageCount", "100")))
                    .genre(pAttributes.getOrDefault("genre", "General"))
                    .build();

            case MAGAZINE -> new Magazine(
                    IdGenerator.INSTANCE.nextBookId(),
                    title,
                    year,
                    true,
                    Collections.emptyList(),
                    Integer.parseInt(pAttributes.getOrDefault("issueNumber", "1")),
                    pAttributes.getOrDefault("publicationMonth", "JANUARY")
            );

            case DVD -> new Dvd(
                    IdGenerator.INSTANCE.nextBookId(),
                    title,
                    year,
                    true,
                    Collections.emptyList(),
                    Integer.parseInt(pAttributes.getOrDefault("durationMinutes", "120")),
                    pAttributes.getOrDefault("director", "Unknown Director")
            );
        };
    }
}