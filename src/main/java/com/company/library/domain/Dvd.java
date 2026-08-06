package com.company.library.domain;

public final class Dvd extends LibraryItem {

    private int duration;
    private String director;

    public Dvd(String id, String title, int publicationYear, boolean isAvailable, int duration, String director) {
        super(id, title, publicationYear, isAvailable);
        this.duration = duration;
        this.director = director;
    }

    @Override
    public String describe() {
        return "DVD: " + getTitle() + " directed by " + director + " (" + duration + " mins)";
    }

    @Override
    public double replacementValue() {
        return 15.0;
    }

    @Override
    public int loanPeriodDays() {
        return 7;
    }

    public int getDuration() {
        return duration;
    }

    public String getDirector() {
        return director;
    }
}