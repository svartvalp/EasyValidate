package com.svartvalp.EasyValidate.Exceptions;

public class FieldNotSupportedTypeException extends RuntimeException {
    public FieldNotSupportedTypeException(String fieldName) {
        super("in field " + fieldName + " type not supported");
    }
}
