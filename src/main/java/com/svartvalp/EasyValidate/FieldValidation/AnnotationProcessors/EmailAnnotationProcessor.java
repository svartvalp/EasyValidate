package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.FieldNotSupportedTypeException;
import com.svartvalp.EasyValidate.Exceptions.InternalValidatorError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

/* supports CharSequence
 * @return ValidationResult - result of validation
 * @param field - field to validate
 * @param object - object to validate
 * uses Email annotation to provide email validation
 */


public class EmailAnnotationProcessor implements AnnotationProcessor {
    @Override
    public ValidationResult validate(Field field, Object object) {
        try {
            Object value = field.get(object);
            if(value == null) return new FieldValidationResult();
            if(value instanceof CharSequence) {
                CharSequence textValue = (CharSequence) value;
                if (Pattern.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", textValue)) {
                    return new FieldValidationResult();
                } else return new FieldValidationResult(false,
                        List.of(new FieldValidationError(field.getName(),"must be an email")));
            } else throw new FieldNotSupportedTypeException(field.getName());
        } catch (IllegalAccessException e) {
            throw new InternalValidatorError();
        }
    }
}
