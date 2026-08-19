package com.company.library;

import com.company.library.config.IdGenerator;
import com.company.library.config.LibraryConfig;
import com.company.library.domain.ItemType;
import com.company.library.domain.LibraryItem;
import com.company.library.factory.LibraryItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LibraryConfig config = LibraryConfig.INSTANCE;
        log.info("Starting {}...", config.getLibraryName());
        log.info("Hours: {} | Fine Rate: ${}", config.getOpeningHours(), config.getFineRate());

        IdGenerator idGen = IdGenerator.getInstance();

        LibraryItem book = LibraryItemFactory.createItem(ItemType.BOOK, Map.of(
                "id", idGen.nextBookId(),
                "title", "Design Patterns",
                "author", "Gang of Four",
                "isbn", "978-0201633610",
                "publicationYear", "1994",
                "pageCount", "395"
        ));

        LibraryItem mag = LibraryItemFactory.createItem(ItemType.MAGAZINE, Map.of(
                "id", idGen.nextBookId(),
                "title", "Java Magazine",
                "publicationYear", "2026",
                "issueNumber", "42",
                "publicationMonth", "August"
        ));

        LibraryItem dvd = LibraryItemFactory.createItem(ItemType.DVD, Map.of(
                "id", idGen.nextBookId(),
                "title", "Inception",
                "publicationYear", "2010",
                "durationMinutes", "148",
                "director", "Christopher Nolan"
        ));

        List<LibraryItem> items = List.of(book, mag, dvd);
        for (LibraryItem item : items) {
            log.info("{} | Loan: {} days | Replace: ${}", item.describe(), item.loanPeriodDays(), item.replacementValue());
        }
    }
}