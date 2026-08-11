package com.company.library.domain;

import com.company.library.exception.ValidationException;
import com.company.library.util.IdGenerator;
import java.math.BigDecimal;
import java.util.List;

public final class Book extends LibraryItem {
    private final String isbn;
    private final String author;
    private final int pageCount;
    private final String genre;

    private Book(Builder pBuilder) {
        super(pBuilder.id, pBuilder.title, pBuilder.publicationYear, true, pBuilder.tags);
        this.isbn = pBuilder.isbn;
        this.author = pBuilder.author;
        this.pageCount = pBuilder.pageCount;
        this.genre = pBuilder.genre;
    }

    @Override
    public Book copy() {
        // Deep copy creates a fresh ID while cloning bibliographic details and tags
        return new Builder()
                .id(IdGenerator.INSTANCE.nextBookId())
                .title(getTitle())
                .publicationYear(getPublicationYear())
                .tags(getTags())
                .isbn(this.isbn)
                .author(this.author)
                .pageCount(this.pageCount)
                .genre(this.genre)
                .build();
    }

    @Override
    public int loanPeriodDays() { return 14; }

    @Override
    public BigDecimal replacementValue() { return new BigDecimal("29.99"); }

    @Override
    public String describe() { return "Book: " + getTitle() + " by " + author + " (ISBN: " + isbn + ")"; }

    public String getIsbn() { return isbn; }
    public String getAuthor() { return author; }
    public int getPageCount() { return pageCount; }
    public String getGenre() { return genre; }

    public static class Builder {
        private String id;
        private String title;
        private int publicationYear;
        private List<String> tags;
        private String isbn;
        private String author;
        private int pageCount = 1;
        private String genre;

        public Builder id(String pId) { this.id = pId; return this; }
        public Builder title(String pTitle) { this.title = pTitle; return this; }
        public Builder publicationYear(int pYear) { this.publicationYear = pYear; return this; }
        public Builder tags(List<String> pTags) { this.tags = pTags; return this; }
        public Builder isbn(String pIsbn) { this.isbn = pIsbn; return this; }
        public Builder author(String pAuthor) { this.author = pAuthor; return this; }
        public Builder pageCount(int pPages) { this.pageCount = pPages; return this; }
        public Builder genre(String pGenre) { this.genre = pGenre; return this; }

        private void validate() {
            if (isbn == null || isbn.isBlank()) {
                throw new ValidationException("ISBN cannot be null or blank", "isbn", isbn);
            }
            if (id == null || id.isBlank()) {
                this.id = this.isbn;
            }
            if (title == null || title.isBlank()) {
                throw new ValidationException("Book title must not be null or blank", "title", title);
            }
            if (author == null || author.isBlank()) {
                throw new ValidationException("Book author must not be null or blank", "author", author);
            }
            if (publicationYear <= 0) {
                throw new ValidationException("Publication year must be greater than 0", "publicationYear", publicationYear);
            }
            if (pageCount <= 0) {
                throw new ValidationException("Page count must be greater than zero", "pageCount", pageCount);
            }
        }

        public Book build() {
            validate();
            return new Book(this);
        }
    }
}