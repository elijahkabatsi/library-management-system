package com.company.library.domain;

import com.company.library.util.IdGenerator;
import java.math.BigDecimal;
import java.util.List;

public final class Magazine extends LibraryItem {
    private final int issueNumber;
    private final String publicationMonth;

    public Magazine(String pId, String pTitle, int pPublicationYear, boolean pAvailable, List<String> pTags, int pIssueNumber, String pPublicationMonth) {
        super(pId, pTitle, pPublicationYear, pAvailable, pTags);
        this.issueNumber = pIssueNumber;
        this.publicationMonth = pPublicationMonth;
    }

    @Override
    public int loanPeriodDays() { return 7; }

    @Override
    public BigDecimal replacementValue() { return new BigDecimal("9.99"); }

    @Override
    public String describe() { return "Magazine: " + getTitle() + " Issue #" + issueNumber; }

    @Override
    public Magazine copy() {
        return new Magazine(
                IdGenerator.INSTANCE.nextBookId(),
                getTitle(),
                getPublicationYear(),
                isAvailable(),
                getTags(),
                this.issueNumber,
                this.publicationMonth
        );
    }

    public int getIssueNumber() { return issueNumber; }
    public String getPublicationMonth() { return publicationMonth; }
}