package com.company.library;

import com.company.library.config.LibraryConfig;
import com.company.library.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Initializing Library System...");

        LibraryConfig configInstance = LibraryConfig.INSTANCE;
        logger.info("Library Name: {}", configInstance.getLibraryName());
        logger.info("Fine Rate per Day: {}", configInstance.getFineRate());

        String bookId1 = IdGenerator.INSTANCE.nextBookId();
        String bookId2 = IdGenerator.INSTANCE.nextBookId();
        String memberId1 = IdGenerator.INSTANCE.nextMemberId();

        logger.info("Generated Identifiers: {}, {}, {}", bookId1, bookId2, memberId1);
    }
}
