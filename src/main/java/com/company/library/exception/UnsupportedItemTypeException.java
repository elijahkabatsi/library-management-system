package com.company.library.exception;

public class UnsupportedItemTypeException extends RuntimeException {
    public UnsupportedItemTypeException(String message) {
        super(message);
    }
}