package com.company.library.domain;

import com.company.library.exception.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public final class Book extends LibraryItem {
    private final String isbn;
    private final String author;
    private final int pageCount;
    private final String genre;

    private Book(Builder builder) {
        super(builder.id, builder.title, builder.publicationYear, builder.available);
        this.isbn = builder.isbn;
        this.author = builder.author;
        this.pageCount = builder.pageCount;
        this.genre = builder.genre;
    }

    // Getters for Book-specific fields
    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getGenre() {
        return genre;
    }

    // Abstract method implementations required by LibraryItem
    @Override
    public int loanPeriodDays() {
        return 14;
    }

    @Override
    public double replacementValue() {
        return 19.99;
    }

    @Override
    public String describe() {
        return String.format("Book [ID: %s, Title: %s, Author: %s, ISBN: %s]",
                getId(), getTitle(), author, isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    // Builder Implementation
    public static class Builder {
        private String id;
        private String title;
        private int publicationYear;
        private boolean available = true;
        private String isbn;
        private String author;
        private int pageCount = 1;
        private String genre;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder publicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public Builder available(boolean available) {
            this.available = available;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder pageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            validate();
            return new Book(this);
        }

        private void validate() {
            if (id == null || id.isBlank()) {
                this.id = this.isbn;
            }
            if (title == null || title.isBlank()) {
                throw new ValidationException("Book title must not be null or blank");
            }
            if (isbn == null || isbn.isBlank()) {
                throw new ValidationException("ISBN cannot be null or blank");
            }
            if (author == null || author.isBlank()) {
                throw new ValidationException("Book author must not be null or blank");
            }
            if (publicationYear <= 0) {
                throw new ValidationException("Publication year must be greater than 0");
            }
            if (pageCount <= 0) {
                throw new ValidationException("Page count must be greater than zero");
            }
        }
    }
}