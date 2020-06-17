package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PatternAnnotationProcessorTest {

    @Test
    void validateTest() throws NoSuchFieldException {
        TestClass testClass = new TestClass();
        testClass.setThree("1");
        PatternAnnotationProcessor patternAnnotationProcessor = new PatternAnnotationProcessor();
        Field field = testClass.getClass().getDeclaredField("three");
        field.setAccessible(true);
        var result = patternAnnotationProcessor.validate(field, testClass);
        assertFalse(result.isValid());
        assertEquals(1, result.getValidationErrors().size());
    }

    @Test
    void validateIfNull() throws NoSuchFieldException {
        TestClass testClass = new TestClass();
        PatternAnnotationProcessor patternAnnotationProcessor = new PatternAnnotationProcessor();
        Field field = testClass.getClass().getDeclaredField("three");
        field.setAccessible(true);
        var result = patternAnnotationProcessor.validate(field, testClass);
        assertTrue(result.isValid());
    }
}