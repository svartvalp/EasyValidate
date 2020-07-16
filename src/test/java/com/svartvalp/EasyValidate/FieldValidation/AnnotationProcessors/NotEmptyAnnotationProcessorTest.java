package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestSubClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyAnnotationProcessorTest {

    @Test
    void validate() throws NoSuchFieldException {
        TestSubClass testSubClass = new TestSubClass();
        testSubClass.setFive(new int[0]);
        Field field = TestSubClass.class.getField("five");
        field.setAccessible(true);
        NotEmptyAnnotationProcessor processor = new NotEmptyAnnotationProcessor();
        assertFalse(processor.validate(field, testSubClass).isValid());
        testSubClass.setFive(new int[4]);
        assertTrue(processor.validate(field, testSubClass).isValid());
    }
}