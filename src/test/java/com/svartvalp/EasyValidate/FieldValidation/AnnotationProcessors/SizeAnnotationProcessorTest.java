package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestSubClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeAnnotationProcessorTest {

    @Test
    void validateTestMax() throws NoSuchFieldException {
        TestSubClass subClass = new TestSubClass();
        subClass.setFive(new int[8]);
        SizeAnnotationProcessor processor = new SizeAnnotationProcessor();
        var result = processor.validate(subClass.getClass().getField("five"), subClass);
        assertEquals(false, result.isValid());
        assertEquals("five  must be less than or equal 7", result.getValidationErrors().get(0).getMessage());
    }

    @Test
    void validateTestMin() throws NoSuchFieldException{
        TestSubClass subClass = new TestSubClass();
        subClass.setFive(new int[1]);
        SizeAnnotationProcessor processor = new SizeAnnotationProcessor();
        var result = processor.validate(subClass.getClass().getField("five"), subClass);
        assertEquals(false, result.isValid());
        assertEquals("five  must be more than or equal 2", result.getValidationErrors().get(0).getMessage());
    }

    @Test
    void validateTestNullField() throws NoSuchFieldException {
        TestSubClass subClass = new TestSubClass();
        SizeAnnotationProcessor processor = new SizeAnnotationProcessor();
        assertDoesNotThrow(() -> processor.validate(subClass.getClass().getField("five"), subClass));
    }


}