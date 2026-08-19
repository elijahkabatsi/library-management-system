package com.company.library.domain;

import java.math.BigDecimal;
import java.util.Objects;

public abstract sealed class LibraryItem permits Book, Magazine, Dvd {
    private final String id;
    private final String title;
    private final int publicationYear;
    private final boolean available;

    public LibraryItem(String id, String title, int publicationYear, boolean available) {
        this.id = Objects.requireNonNull(id, "ID must not be null");
        this.title = Objects.requireNonNull(title, "Title must not be null");
        this.publicationYear = publicationYear;
        this.available = available;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return available; }

    public abstract int loanPeriodDays();
    public abstract BigDecimal replacementValue();
    public abstract String describe();
}