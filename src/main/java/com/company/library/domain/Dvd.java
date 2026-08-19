package com.company.library.domain;

import java.math.BigDecimal;

public final class Dvd extends LibraryItem {
    private final int durationMinutes;
    private final String director;

    public Dvd(String id, String title, int publicationYear, boolean available, int durationMinutes, String director) {
        super(id, title, publicationYear, available);
        this.durationMinutes = durationMinutes;
        this.director = director;
    }

    @Override
    public int loanPeriodDays() { return 3; }

    @Override
    public BigDecimal replacementValue() {
        return new BigDecimal("20.00");
    }

    @Override
    public String describe() {
        return "DVD: " + getTitle() + " directed by " + director + " (" + durationMinutes + " mins)";
    }

    public int getDurationMinutes() { return durationMinutes; }
    public String getDirector() { return director; }
}