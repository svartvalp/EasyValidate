package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.FieldNotSupportedTypeException;
import com.svartvalp.EasyValidate.Exceptions.InternalValidatorError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationError;
import com.svartvalp.EasyValidate.FieldValidation.FieldValidationResult;
import com.svartvalp.EasyValidate.ValidationResult;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Field;
import java.util.List;


/* supports CharSequence and it's implementations
 * @return ValidationResult - result of validation
 * @param field - field to validate
 * @param object - object to validate
 * Pattern annotation check that field matches regexp.
 */

public class PatternAnnotationProcessor implements AnnotationProcessor {
    @Override
    public ValidationResult validate(Field field, Object object) {
        try {
            var fieldObject = field.get(object);
            if(fieldObject == null) {
                return new  FieldValidationResult();
            }
            var patternAnnotation = field.getAnnotation(Pattern.class);
            if(patternAnnotation == null) {
                throw new InternalValidatorError();
            }
            var regexp = patternAnnotation.regexp();
            if(fieldObject instanceof CharSequence) {
                if (java.util.regex.Pattern.compile(regexp)
                        .matcher((CharSequence) fieldObject)
                        .matches()) {
                    return new FieldValidationResult();
                } else {
                    FieldValidationError error = new FieldValidationError(field.getName(),
                            "doesn't match the pattern");
                    return new FieldValidationResult(false, List.of(error));
                }
            } else throw new FieldNotSupportedTypeException(field.getName());
        } catch (IllegalAccessException e) {
            throw new InternalValidatorError();
        }
    }
}
