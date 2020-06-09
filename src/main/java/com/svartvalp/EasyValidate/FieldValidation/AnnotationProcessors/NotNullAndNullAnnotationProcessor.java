package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.BadConstraintException;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationResult;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.lang.reflect.Field;
import java.util.List;


/*
 * Accepts all types
 */


public class NotNullAndNullAnnotationProcessor implements AnnotationProcessor {

    public ValidationResult validate(Field field, Object object) {
        try {
            var value = field.get(object);
            var notNullAnnotation = field.getAnnotation(NotNull.class);
            var nullAnnotation = field.getAnnotation(Null.class);
            if(notNullAnnotation != null && nullAnnotation != null) {
                throw new BadConstraintException(field.getName() + "cannot be null and not null simultaneously");
            }
            if(notNullAnnotation != null) {
                if(value == null) {
                    return new FieldValidationResult(false,
                            List.of(new FieldValidationError(field.getName(), " must be not null")));
                }
            }
            if(nullAnnotation != null) {
                if(value != null) {
                    return new FieldValidationResult(false,
                            List.of(new FieldValidationError(field.getName(), " must be null")));
                }
            }
            return new FieldValidationResult();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new FieldValidationResult();
    }


}
