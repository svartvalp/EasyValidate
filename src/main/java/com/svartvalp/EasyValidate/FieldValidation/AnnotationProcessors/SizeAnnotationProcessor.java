package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.FieldNotSupportedTypeException;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationError;
import com.svartvalp.EasyValidate.ValidationResult;

import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;



public class SizeAnnotationProcessor implements AnnotationProcessor {

    /*
     * supports all types, which support @Size annotation (javax.validation.constraints)
     *  @param field - field to validate
     * @param object - object to validate
     * @throws FieldNotSupportedTypeException
     */
    public ValidationResult validate(Field field, Object object) {
        var annotation = field.getAnnotation(Size.class);
        int min = annotation.min();
        int max = annotation.max();
        field.setAccessible(true);
        Object fieldToValidate = null;
        try {
            fieldToValidate = field.get(object);
            if(fieldToValidate == null) {
                return new FieldValidationResult();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int size = -1;
        if(fieldToValidate instanceof CharSequence) {
            size = ((CharSequence) fieldToValidate).length();
        }
        if(fieldToValidate instanceof Map) {
            size = ((Map) fieldToValidate).size();
        }

        if(fieldToValidate instanceof Collection) {
            size = ((Collection) fieldToValidate).size();
        }

        if(fieldToValidate.getClass().isArray()) {
            size = Array.getLength(fieldToValidate);
        }
        if(size == -1) {
            throw new FieldNotSupportedTypeException(field.getName());
        }
        List<ValidationError> errors = new ArrayList<>();
        if(size > max) {
            errors.add(new FieldValidationError(field.getName(), " must be less than or equal " + max));
        }
        if(size < min) {
            errors.add(new FieldValidationError(field.getName(), " must be more than or equal " + min));
        }
        if(errors.size() == 0)
            return new FieldValidationResult();
        return new FieldValidationResult(false, errors);
    }



}
