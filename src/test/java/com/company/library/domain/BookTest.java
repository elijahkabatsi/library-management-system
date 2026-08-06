package com.company.library.domain;

import com.company.library.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

    @Test
    @DisplayName("Should successfully build a Book when all fields are valid")
    void shouldCreateBookSuccessfully() {
        Book book = new Book.Builder()
                .isbn("978-0134685991")
                .title("Effective Java")
                .author("Joshua Bloch")
                .publicationYear(2018)
                .build();

        assertThat(book).isNotNull();
        assertThat(book.getIsbn()).isEqualTo("978-0134685991");
        assertThat(book.getTitle()).isEqualTo("Effective Java");
        assertThat(book.getAuthor()).isEqualTo("Joshua Bloch");
        assertThat(book.getPublicationYear()).isEqualTo(2018);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("Should throw ValidationException when ISBN is blank or empty")
    void shouldThrowExceptionWhenIsbnIsInvalid(String invalidIsbn) {
        assertThatThrownBy(() -> new Book.Builder()
                .isbn(invalidIsbn)
                .title("Clean Code")
                .author("Robert C. Martin")
                .publicationYear(2008)
                .build())
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("ISBN cannot be null or blank");
    }

    @Test
    @DisplayName("Should throw ValidationException when publication year is 0 or negative")
    void shouldThrowExceptionWhenYearIsInvalid() {
        assertThatThrownBy(() -> new Book.Builder()
                .isbn("12345")
                .title("Domain-Driven Design")
                .author("Eric Evans")
                .publicationYear(0)
                .build())
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Publication year must be greater than 0");
    }

    @Test
    @DisplayName("Should verify equality based on ISBN")
    void shouldVerifyEqualityBasedOnIsbn() {
        Book book1 = new Book.Builder()
                .isbn("978-0134685991")
                .title("Effective Java")
                .author("Joshua Bloch")
                .publicationYear(2018)
                .build();

        Book book2 = new Book.Builder()
                .isbn("978-0134685991")
                .title("Different Title")
                .author("Different Author")
                .publicationYear(2020)
                .build();

        assertThat(book1).isEqualTo(book2);
        assertThat(book1.hashCode()).isEqualTo(book2.hashCode());
    }
}