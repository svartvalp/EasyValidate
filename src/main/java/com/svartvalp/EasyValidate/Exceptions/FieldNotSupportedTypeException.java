package com.svartvalp.EasyValidate.Exceptions;

/*
 * throws when some annotation don't support field type
 */



public class FieldNotSupportedTypeException extends RuntimeException {
    public FieldNotSupportedTypeException(String fieldName) {
        super("in field " + fieldName + " type not supported");
    }
}
