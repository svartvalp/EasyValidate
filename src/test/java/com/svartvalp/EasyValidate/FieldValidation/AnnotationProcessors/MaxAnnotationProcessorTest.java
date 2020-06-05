package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestSubClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxAnnotationProcessorTest {

    @Test
    void validateTest() throws NoSuchFieldException {
        TestSubClass testSubClass = new TestSubClass();
        testSubClass.setOne(100);
        MaxAnnotationProcessor processor = new MaxAnnotationProcessor();
        var result = processor.validate(TestSubClass.class.getField("one"), testSubClass);
        assertFalse(result.isValid());
    }

    @Test
    void validateTestNullField() {
        TestSubClass testSubClass = new TestSubClass();
        MaxAnnotationProcessor processor = new MaxAnnotationProcessor();
        assertDoesNotThrow(() -> processor.validate(TestSubClass.class.getField("one"), testSubClass));
    }
}