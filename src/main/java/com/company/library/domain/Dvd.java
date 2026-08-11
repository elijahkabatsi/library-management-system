package com.company.library.domain;

import com.company.library.util.IdGenerator;
import java.math.BigDecimal;
import java.util.List;

public final class Dvd extends LibraryItem {
    private final int durationMinutes;
    private final String director;

    public Dvd(String pId, String pTitle, int pPublicationYear, boolean pAvailable, List<String> pTags, int pDurationMinutes, String pDirector) {
        super(pId, pTitle, pPublicationYear, pAvailable, pTags);
        this.durationMinutes = pDurationMinutes;
        this.director = pDirector;
    }

    @Override
    public int loanPeriodDays() { return 3; }

    @Override
    public BigDecimal replacementValue() { return new BigDecimal("19.99"); }

    @Override
    public String describe() { return "DVD: " + getTitle() + " directed by " + director; }

    @Override
    public Dvd copy() {
        return new Dvd(
                IdGenerator.INSTANCE.nextBookId(),
                getTitle(),
                getPublicationYear(),
                isAvailable(),
                getTags(),
                this.durationMinutes,
                this.director
        );
    }

    public int getDurationMinutes() { return durationMinutes; }
    public String getDirector() { return director; }
}