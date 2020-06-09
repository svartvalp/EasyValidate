package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestSubClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullAndNullAnnotationProcessorTest {

    @Test
    void validateTest() throws NoSuchFieldException {
        NotNullAndNullAnnotationProcessor proccesor = new NotNullAndNullAnnotationProcessor();
        TestSubClass testClass = new TestSubClass();
        var result = proccesor.validate(testClass.getClass().getField("five"), testClass);
        assertFalse(result.isValid());
        assertEquals(1, result.getValidationErrors().size());
    }

}