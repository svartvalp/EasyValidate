package com.svartvalp.EasyValidate.FieldValidation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldObjectAnnotationValidatorTest {


    @Test
    void validateTest() {
        var fieldObjectAnnotationValidator = new FieldObjectAnnotationValidator();
        TestSubClass testSubClass = new TestSubClass();
        testSubClass.setFive(new int[10]);
        var result = fieldObjectAnnotationValidator.validate(testSubClass, "five");
        assertEquals(1, result.getValidationErrors().size());
        assertFalse(result.isValid());
    }

    @Test
    void validateTestEmptyFieldNameList() {
        var fieldObjectAnnotationValidator = new FieldObjectAnnotationValidator();
        TestSubClass testSubClass = new TestSubClass();
        testSubClass.setFive(new int[10]);
        testSubClass.setOne(100);
        testSubClass.setFour(false);
        var result = fieldObjectAnnotationValidator.validate(testSubClass);
        result.getValidationErrors().forEach((error) -> System.out.println(error.getMessage()));
        assertEquals(4, result.getValidationErrors().size());
        assertFalse(result.isValid());
    }

}