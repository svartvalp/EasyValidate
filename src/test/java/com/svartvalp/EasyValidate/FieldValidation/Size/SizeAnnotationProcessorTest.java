package com.svartvalp.EasyValidate.FieldValidation.Size;

import com.svartvalp.EasyValidate.FieldValidation.TestSubClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeAnnotationProcessorTest {

    @Test
    void validateTestMax() throws NoSuchFieldException, IllegalAccessException {
        TestSubClass subClass = new TestSubClass();
        subClass.setFive(new int[8]);
        SizeAnnotationProcessor processor = new SizeAnnotationProcessor();
        var result = processor.validate(subClass.getClass().getField("five"), subClass);
        assertEquals(false, result.isValid());
        assertEquals("five  must be less than 7", result.getValidationErrors().get(0).getMessage());
    }

    @Test
    void validateTestMin() throws NoSuchFieldException, IllegalAccessException {
        TestSubClass subClass = new TestSubClass();
        subClass.setFive(new int[1]);
        SizeAnnotationProcessor processor = new SizeAnnotationProcessor();
        var result = processor.validate(subClass.getClass().getField("five"), subClass);
        assertEquals(false, result.isValid());
        assertEquals("five  must be more than 2", result.getValidationErrors().get(0).getMessage());
    }


}