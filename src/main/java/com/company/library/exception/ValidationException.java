package com.company.library.exception;

public class ValidationException extends LibraryException {
    private String field;
    private Object rejectedValue;

    public ValidationException(String pMessage) {
        super(pMessage);
    }

    public ValidationException(String pMessage, String pField, Object pRejectedValue) {
        super(pMessage + " [Field: " + pField + ", Value: " + pRejectedValue + "]");
        this.field = pField;
        this.rejectedValue = pRejectedValue;
    }

    public String getField() { return field; }
    public Object getRejectedValue() { return rejectedValue; }
}