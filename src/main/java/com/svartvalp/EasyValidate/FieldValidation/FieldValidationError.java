package com.svartvalp.EasyValidate.FieldValidation;

import com.svartvalp.EasyValidate.ValidationError;

public class FieldValidationError implements ValidationError {

    String fieldName;
    String errorMessage;

    public FieldValidationError() {
    }

    public FieldValidationError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return fieldName + " " + errorMessage;
    }
}
