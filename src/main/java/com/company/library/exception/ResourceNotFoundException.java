package com.company.library.exception;

public class ResourceNotFoundException extends LibraryException {
    public ResourceNotFoundException(String message) {
        super (message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
