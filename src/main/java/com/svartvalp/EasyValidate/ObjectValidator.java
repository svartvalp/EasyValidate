package com.svartvalp.EasyValidate;

public interface ObjectValidator {
    public ValidationResult validate(Object object, String... fields);

}
