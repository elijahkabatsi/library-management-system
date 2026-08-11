// File: src/test/java/com/company/library/domain/PrototypeTest.java
package com.company.library.domain;

import com.company.library.exception.ItemNotFoundException;
import com.company.library.util.PrototypeRegistry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PrototypeTest {

    @Test
    void shouldDeepCopyTagsAndPreventMutationOfOriginal() {
        List<String> originalTags = new ArrayList<>(List.of("Java", "Programming"));
        Book originalBook = new Book.Builder()
                .id("BK-001")
                .title("Clean Code")
                .author("Robert Martin")
                .isbn("978-0132350884")
                .publicationYear(2008)
                .tags(originalTags)
                .build();

        Book clonedBook = originalBook.copy();
        clonedBook.getTags().add("Refactoring"); // Mutating clone tag list

        assertThat(originalBook.getTags()).containsExactly("Java", "Programming");
        assertThat(clonedBook.getId()).isNotEqualTo(originalBook.getId());
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenKeyIsUnknown() {
        PrototypeRegistry registry = new PrototypeRegistry();

        assertThatThrownBy(() -> registry.get("unknown-template"))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("unknown-template");
    }

    @Test
    void shouldThrowIllegalStateExceptionOnDuplicateRegistration() {
        PrototypeRegistry registry = new PrototypeRegistry();
        Book sampleBook = new Book.Builder()
                .title("Effective Java")
                .author("Joshua Bloch")
                .isbn("978-0134685991")
                .publicationYear(2018)
                .build();

        registry.registerTemplate("paperback", sampleBook);

        assertThatThrownBy(() -> registry.registerTemplate("paperback", sampleBook))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("paperback");
    }

    @Test
    void shouldThrowNullPointerExceptionWhenComparingToNull() {
        Book book = new Book.Builder()
                .title("Java in Action")
                .author("Author Name")
                .isbn("978-1617293566")
                .publicationYear(2018)
                .build();

        assertThatThrownBy(() -> book.compareTo(null))
                .isInstanceOf(NullPointerException.class);
    }
}