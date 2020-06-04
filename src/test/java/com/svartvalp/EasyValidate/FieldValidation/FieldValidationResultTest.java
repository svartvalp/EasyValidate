package com.svartvalp.EasyValidate.FieldValidation;

import com.svartvalp.EasyValidate.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldValidationResultTest {


    @Test
    public void testCopyConstructor() {
        var first = new FieldValidationResult();
        ValidationError error = new ValidationError() {
            @Override
            public String getMessage() {
                return "hello";
            }
        };
        var second = new FieldValidationResult(false, List.of(error));
        var third = new FieldValidationResult();
        FieldValidationResult fieldValidationResult = new FieldValidationResult(first, second, third);
        assertFalse(fieldValidationResult.isValid());
        assertEquals(1, fieldValidationResult.getValidationErrors().size());

    }

}