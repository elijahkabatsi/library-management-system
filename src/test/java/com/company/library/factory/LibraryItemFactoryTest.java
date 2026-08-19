package com.company.library.factory;

import com.company.library.domain.Dvd;
import com.company.library.domain.LibraryItem;
import com.company.library.exception.UnsupportedItemTypeException;

public class LibraryItemFactoryTest {

    public static LibraryItem createItem(String type, String id, String title, int year, int durationOrPages, String extraDetails) {
        if ("DVD".equalsIgnoreCase(type)) {
            return new Dvd(id, title, year, true, durationOrPages, extraDetails);
        }
        throw new UnsupportedItemTypeException("Unsupported item type: " + type);
    }

    public static LibraryItem createItem(String type, String id, String title, int year) {
        throw new UnsupportedItemTypeException("Unsupported item type: " + type);
    }
}