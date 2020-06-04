package com.svartvalp.EasyValidate;

import java.util.List;

public interface ValidationResult {

    public boolean isValid();
    public List<ValidationError> getValidationErrors();

}
