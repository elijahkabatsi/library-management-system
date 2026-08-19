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
    public int loanPeriodDays() { return 7; }

    @Override
    public BigDecimal replacementValue() {
        return new BigDecimal("10.00");
    }

    @Override
    public String describe() {
        return "Magazine: " + getTitle() + " - Issue #" + issueNumber + " (" + publicationMonth + ")";
    }

    public int getIssueNumber() { return issueNumber; }
    public String getPublicationMonth() { return publicationMonth; }
}