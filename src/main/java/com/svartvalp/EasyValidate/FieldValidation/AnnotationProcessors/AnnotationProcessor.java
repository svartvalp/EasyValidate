package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.ValidationResult;

import java.lang.reflect.Field;

public interface AnnotationProcessor {
    public ValidationResult validate(Field field, Object object);
}
