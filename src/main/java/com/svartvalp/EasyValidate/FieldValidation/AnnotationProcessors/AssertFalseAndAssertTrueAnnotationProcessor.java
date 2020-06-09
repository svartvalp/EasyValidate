package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.BadConstraintException;
import com.svartvalp.EasyValidate.Exceptions.FieldNotSupportedTypeException;
import com.svartvalp.EasyValidate.Exceptions.InternalValidatorError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationResult;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.lang.reflect.Field;
import java.util.List;

public class AssertFalseAndAssertTrueAnnotationProcessor implements AnnotationProcessor {
    @Override
    public ValidationResult validate(Field field, Object object) {
        var clazz = field.getType();
        if(clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            try {
                boolean value = (boolean) field.get(object);
                var assertTrue = field.getAnnotation(AssertTrue.class);
                var assertFalse = field.getAnnotation(AssertFalse.class);
                if(assertFalse != null && assertTrue != null) {
                    throw new BadConstraintException("field" + field.getName()
                            + " cannot be true and false simultaneously");
                }
                if(assertTrue != null) {
                    if(!value) {
                        return new FieldValidationResult(false,
                                List.of(new FieldValidationError(field.getName(), "must be true")));
                    }
                }
                if(assertFalse != null) {
                    if(value) {
                        return new FieldValidationResult(false,
                                List.of(new FieldValidationError(field.getName(), "must be false")));
                    }
                }
                return new FieldValidationResult();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
       throw new FieldNotSupportedTypeException(field.getName());
    }
}
