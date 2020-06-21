package com.svartvalp.EasyValidate.FieldValidation;

import com.svartvalp.EasyValidate.ValidationError;
import com.svartvalp.EasyValidate.ValidationResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


public class FieldValidationResult implements ValidationResult {

    private boolean isValid;
    private List<ValidationError> errors;

    public FieldValidationResult(boolean isValid, List<ValidationError> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public FieldValidationResult(List<FieldValidationResult> results) {
        errors = new LinkedList<>();
        isValid = results.stream().map(result -> {
            errors.addAll(result.getValidationErrors());
            return result.isValid();
        }).reduce((a,b) -> a && b).orElse(true);
    }

    public FieldValidationResult() {
        isValid = true;
        errors = new LinkedList<>();
    }

    public FieldValidationResult(FieldValidationResult... results) {
        errors = new LinkedList<>();
        isValid = Stream.of(results).map(fieldValidationResult -> {
            errors.addAll(fieldValidationResult.getValidationErrors());
            return fieldValidationResult.isValid();
        }).reduce((a,b) -> a && b).orElse( true);
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public List<ValidationError> getValidationErrors() {
        return errors;
    }
}
