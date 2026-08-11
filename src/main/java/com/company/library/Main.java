package com.company.library;

import com.company.library.config.LibraryConfig;
import com.company.library.domain.Book;
import com.company.library.domain.LibraryItem;
import com.company.library.factory.ItemType;
import com.company.library.factory.LibraryItemFactory;
import com.company.library.util.IdGenerator;
import com.company.library.util.PrototypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("PHASE 1 VERIFICATION (DAYS 1 - 4) ");

        // DAY 3: Configuration Loading & Thread-Safe ID Generation
        LibraryConfig config = LibraryConfig.INSTANCE;
        logger.info("[DAY 3] Config Loaded: '{}' | Fine Rate: ${}/day",
                config.getLibraryName(), config.getFineRate());

        // DAY 1: Builder Pattern & Immutable Objects
        Book day1Book = new Book.Builder()
                .id(IdGenerator.INSTANCE.nextBookId())
                .title("Eli Code")
                .author("Elijah D. Kabatsi")
                .isbn("978-0132350884")
                .publicationYear(2008)
                .pageCount(464)
                .genre("Software")
                .tags(List.of("Tech", "BestPractices"))
                .build();
        logger.info("[DAY 1] Builder Created Book: ID={} | Title='{}'", day1Book.getId(), day1Book.getTitle());

        // DAY 2: Factory Method & Polymorphism
        LibraryItem day2Magazine = LibraryItemFactory.createItem(
                ItemType.MAGAZINE,
                Map.of(
                        "title", "Java Magazine",
                        "publicationYear", "2026",
                        "issueNumber", "42",
                        "publicationMonth", "AUGUST"
                )
        );
        logger.info("[DAY 2] Factory Created Item: Title='{}' | Loan Period: {} days",
                day2Magazine.getTitle(), day2Magazine.loanPeriodDays());

        // DAY 4: Prototype Pattern & Deep Copy Verification
        PrototypeRegistry registry = new PrototypeRegistry();
        registry.registerTemplate("standard-paperback", day1Book);

        // Retrieve a clone from registry
        Book clonedBook = (Book) registry.get("standard-paperback");

        // Mutate the clone's tags
        List<String> cloneTags = clonedBook.getTags();
        cloneTags.add("MUTATED_TAG");

        logger.info("[DAY 4] Original Book Tags: {}", day1Book.getTags());
        logger.info("[DAY 4] Cloned Book Tags:   {}", cloneTags);
        logger.info("[DAY 4] ID Uniqueness Check: Original ID={}, Clone ID={}", day1Book.getId(), clonedBook.getId());

        //POLYMORPHIC COLLECTION DEMONSTRATION
        List<LibraryItem> catalog = new ArrayList<>();
        catalog.add(day1Book);
        catalog.add(day2Magazine);
        catalog.add(clonedBook);

        for (LibraryItem item : catalog) {
            logger.info("Item ID: {} | Description: {}", item.getId(), item.describe());
        }

    }
}