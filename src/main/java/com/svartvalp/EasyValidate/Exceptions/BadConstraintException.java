package com.svartvalp.EasyValidate.Exceptions;

public class BadConstraintException extends RuntimeException {
    public BadConstraintException() {
        super();
    }

    public BadConstraintException(String message) {
        super(message);
    }
}
