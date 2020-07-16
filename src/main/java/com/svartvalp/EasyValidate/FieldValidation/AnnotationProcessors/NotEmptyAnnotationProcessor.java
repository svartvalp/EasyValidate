package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.FieldNotSupportedTypeException;
import com.svartvalp.EasyValidate.Exceptions.InternalValidatorError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationResult;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 * supports CharSequence, Collection, Map, Array
 * @return ValidationResult - result of validation
 * @param field - field to validate
 * @param object - object to validate
 */

public class NotEmptyAnnotationProcessor implements AnnotationProcessor {
    @Override
    public ValidationResult validate(Field field, Object object) {
        try {
            Object fieldObj = field.get(object);
            if (fieldObj == null) {
                return new FieldValidationResult();
            }
            if(fieldObj instanceof CharSequence){
                return ((CharSequence) fieldObj).length() > 0 ?
                        new FieldValidationResult() :
                        new FieldValidationResult(false,
                                List.of(new FieldValidationError(field.getName(), "must be not empty")));
            }
            if(fieldObj instanceof Collection){
                return ((Collection) fieldObj).isEmpty() ?  new FieldValidationResult(false,
                        List.of(new FieldValidationError(field.getName(), "must be not empty")))
                        : new FieldValidationResult();
            }
            if(fieldObj instanceof Map){
                return ((Map) fieldObj).isEmpty() ?  new FieldValidationResult(false,
                        List.of(new FieldValidationError(field.getName(), "must be not empty")))
                        : new FieldValidationResult();
            }
            if(fieldObj.getClass().isArray()) {
                return Array.getLength(fieldObj) > 0 ? new FieldValidationResult() :
                        new FieldValidationResult(false,
                                List.of(new FieldValidationError(field.getName(), "must be not empty")));

            }
            throw new FieldNotSupportedTypeException(field.getName());
        } catch (IllegalAccessException e) {
            throw new InternalValidatorError();
        }
    }
}
