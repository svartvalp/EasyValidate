package com.svartvalp.EasyValidate;

public interface FieldObjectValidator {
    public ValidationResult validate(Object object, String... fields);

}
