package com.company.library.domain;

import com.company.library.exception.ValidationException;

import java.math.BigDecimal;
import java.util.Objects;

public final class Book extends LibraryItem {
    private final String author;
    private final String isbn;
    private final int pageCount;

    private Book(Builder builder) {
        super(builder.id, builder.title, builder.publicationYear, builder.available);
        this.author = builder.author;
        this.isbn = builder.isbn;
        this.pageCount = builder.pageCount;
    }

    @Override
    public int loanPeriodDays() { return 14; }

    @Override
    public BigDecimal replacementValue() {
        return new BigDecimal("25.00");
    }

    @Override
    public String describe() {
        return "Book: " + getTitle() + " by " + author + " (ISBN: " + isbn + ")";
    }

    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPageCount() { return pageCount; }

    public static class Builder {
        private String id;
        private String title;
        private int publicationYear;
        private boolean available = true;
        private String author;
        private String isbn;
        private int pageCount;

        public Builder id(String id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder publicationYear(int publicationYear) { this.publicationYear = publicationYear; return this; }
        public Builder available(boolean available) { this.available = available; return this; }
        public Builder author(String author) { this.author = author; return this; }
        public Builder isbn(String isbn) { this.isbn = isbn; return this; }
        public Builder pageCount(int pageCount) { this.pageCount = pageCount; return this; }

        public Book build() {
            Objects.requireNonNull(title, "Title must not be null");
            if (pageCount <= 0) {
                throw new ValidationException("Page count must be positive: " + pageCount);
            }
            return new Book(this);
        }
    }
}