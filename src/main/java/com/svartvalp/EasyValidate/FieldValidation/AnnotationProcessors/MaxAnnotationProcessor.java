package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.FieldNotSupportedTypeException;
import com.svartvalp.EasyValidate.Exceptions.InternalValidatorError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationResult;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/*
 * supports int, short, long, byte, and BigDecimal and BigInteger (this types support javax.validation.constraints.Max)
 * @return ValidationResult - result of validation
 * @param field - field to validate
 * @param object - object to validate
 * @Min annotation sets maximum value
 */


public class MaxAnnotationProcessor implements AnnotationProcessor {
    public ValidationResult validate(Field field, Object object) {
        Class<?> clazz = field.getType();
        var max = field.getAnnotation(Max.class).value();
        try {
            if (Integer.class.equals(clazz) || int.class.equals(clazz)) {
                int value = field.getInt(object);
                if(value > max) {
                    return getResultWithError(field, max);
                }
                return new FieldValidationResult();
            }
            if (Long.class.equals(clazz) || long.class.equals(clazz)) {
                long value = field.getLong(object);
                if(value > max) {
                    return getResultWithError(field, max);
                }
                return new FieldValidationResult();
            }
            if (Short.class.equals(clazz) || short.class.equals(clazz)) {
                short value = field.getShort(object);
                if(value > max) {
                    return getResultWithError(field, max);
                }
                return new FieldValidationResult();
            }
            if(Byte.class.equals(clazz) || byte.class.equals(clazz)) {
                byte value = field.getByte(object);
                if(value > max) {
                    return getResultWithError(field, max);
                }
                return new FieldValidationResult();
            }
            if (BigDecimal.class.equals(clazz)) {
                var value = (BigDecimal) field.get(object);
                if(value == null || value.compareTo(BigDecimal.valueOf(max)) <= 0) {
                    return new FieldValidationResult();
                }
                return getResultWithError(field, max);
            }
            if (BigInteger.class.equals(clazz)) {
                var value = (BigInteger) field.get(object);
                if(value == null || value.compareTo(BigInteger.valueOf(max)) <= 0) {
                    return new FieldValidationResult();
                }
                return getResultWithError(field, max);
            }
            // if field isn't any instance of supported type, throw an error
            throw new FieldNotSupportedTypeException(field.getName());
            // IllegalAccessException cannot be thrown cause field always have setAccessed(true)
        } catch (IllegalAccessException e) {
            throw new InternalValidatorError();
        }
    }


    private FieldValidationResult getResultWithError(Field field, long min) {
        FieldValidationError error = new FieldValidationError(field.getName(),
                "must be less or equal than " + min);
        return new FieldValidationResult(false, List.of(error));
    }
}
