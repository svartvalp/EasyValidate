package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.FieldValidation.TestClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class EmailAnnotationProcessorTest {


    @Test
    void validateTest() throws NoSuchFieldException {
        EmailAnnotationProcessor processor = new EmailAnnotationProcessor();
        TestClass testClass = new TestClass();
        testClass.setThree("k@m.r");
        Field strField = TestClass.class.getDeclaredField("three");
        strField.setAccessible(true);
        assertTrue(processor.validate(strField, testClass).isValid());
        testClass.setThree("k@m.");
        assertFalse(processor.validate(strField, testClass).isValid());
        testClass.setThree("fasdf");
        assertFalse(processor.validate(strField, testClass).isValid());
    }

}