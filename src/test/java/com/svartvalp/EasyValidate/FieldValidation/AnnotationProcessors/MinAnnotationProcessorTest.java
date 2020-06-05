package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestBaseClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinAnnotationProcessorTest {

    @Test
    void validateTest() throws NoSuchFieldException {
        MinAnnotationProcessor minAnnotationProcessor = new MinAnnotationProcessor();
        TestBaseClass testBaseClass = new TestBaseClass();
        testBaseClass.setOne(1);
        var result = minAnnotationProcessor.validate(testBaseClass.getClass().getField("one"), testBaseClass);
        assertFalse(result.isValid());
        assertEquals("one must be more or equal than 20", result.getValidationErrors().get(0).getMessage());
    }


    @Test
    void validateTestNullField() {
        MinAnnotationProcessor processor = new MinAnnotationProcessor();
        TestBaseClass testBaseClass = new TestBaseClass();
        assertDoesNotThrow(() -> processor.validate(testBaseClass.getClass().getField("one"), testBaseClass));
        assertDoesNotThrow(() -> processor.validate(testBaseClass.getClass().getField("two"), testBaseClass));
    }
}