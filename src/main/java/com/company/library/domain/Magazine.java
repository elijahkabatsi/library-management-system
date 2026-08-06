package com.company.library.domain;

import java.math.BigDecimal;

public final class Magazine extends LibraryItem {
    private final int issueNumber;
    private final String publicationMonth;

    public Magazine(String id, String title, int publicationYear, boolean available, int issueNumber, String publicationMonth) {
        super(id, title, publicationYear, available);
        this.issueNumber = issueNumber;
        this.publicationMonth = publicationMonth;
    }

    @Override
    public int loanPeriodDays() {
        return 7; // Business Rule: 7 days max for magazines
    }

    @Override
    public double replacementValue() {
        return 9.99;
    }

    @Override
    public String describe() {
        return String.format("Magazine [ID: %s, Title: %s, Issue #: %d, Month: %s]",
                getId(), getTitle(), issueNumber, publicationMonth);
    }
}
