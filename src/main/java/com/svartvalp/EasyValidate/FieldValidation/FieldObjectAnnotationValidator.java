package com.svartvalp.EasyValidate.FieldValidation;

import com.svartvalp.EasyValidate.ValidationResult;
import com.svartvalp.EasyValidate.ObjectValidator;

import java.lang.reflect.Field;

public class FieldObjectAnnotationValidator implements ObjectValidator {

    FieldResolver fieldResolver;

    public ValidationResult validate(Object object, String... fields) {

        return null;
    }

    private Field[] getObjectFields(Object object){
        return fieldResolver.getAllFields(object);
    }
}
