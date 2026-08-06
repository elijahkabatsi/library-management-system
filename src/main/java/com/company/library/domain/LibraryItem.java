package com.company.library.domain;

import java.math.BigDecimal;
import java.util.Objects;

public abstract sealed class LibraryItem permits Book, Magazine, Dvd {
    private final String id;
    private final String title;
    private final int publicationYear;
    private final boolean available;

    protected LibraryItem(String id, String title, int publicationYear, boolean available) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.publicationYear = publicationYear;
        this.available = available;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return available; }

    // Abstract methods to be implemented by concrete subclasses
    public abstract int loanPeriodDays();
    public abstract double replacementValue();
    public abstract String describe();
}
