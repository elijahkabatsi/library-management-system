// File: src/main/java/com/company/library/domain/LibraryItem.java
package com.company.library.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract sealed class LibraryItem implements Comparable<LibraryItem> permits Book, Magazine, Dvd {
    private final String id;
    private final String title;
    private final int publicationYear;
    private boolean available;
    private final List<String> tags;

    protected LibraryItem(String pId, String pTitle, int pPublicationYear, boolean pAvailable, List<String> pTags) {
        this.id = pId;
        this.title = pTitle;
        this.publicationYear = pPublicationYear;
        this.available = pAvailable;
        // Defensive copy on constructor input
        this.tags = (pTags != null) ? new ArrayList<>(pTags) : new ArrayList<>();
    }

    public abstract int loanPeriodDays();
    public abstract BigDecimal replacementValue();
    public abstract LibraryItem copy();
    public abstract String describe();

    @Override
    public int compareTo(LibraryItem pOther) {
        Objects.requireNonNull(pOther, "Cannot compare LibraryItem against null reference");
        return this.title.compareTo(pOther.getTitle());
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean pAvailable) { this.available = pAvailable; }

    // Defensive copy on getter output to protect internal list state
    public List<String> getTags() {
        return new ArrayList<>(tags);
    }
}