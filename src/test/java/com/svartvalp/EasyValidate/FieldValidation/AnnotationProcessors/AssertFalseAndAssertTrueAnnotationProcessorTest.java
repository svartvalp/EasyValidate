package com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors;

import com.svartvalp.EasyValidate.Exceptions.BadConstraintException;
import com.svartvalp.EasyValidate.FieldValidation.BooleanTestClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertFalseAndAssertTrueAnnotationProcessorTest {

    @Test
    void validateTest() throws NoSuchFieldException {
        AssertFalseAndAssertTrueAnnotationProcessor processor = new AssertFalseAndAssertTrueAnnotationProcessor();
        BooleanTestClass booleanTestClass = new BooleanTestClass();
        booleanTestClass.setOne(true);
        var result = processor.validate(booleanTestClass.getClass().getField("one"), booleanTestClass);
        assertFalse(result.isValid());
        assertEquals(1, result.getValidationErrors().size());
    }

    @Test
    void validateTestWhenFieldHasAssertTrueAndAssertFalseAnnotation() {
        var processor = new AssertFalseAndAssertTrueAnnotationProcessor();
        BooleanTestClass booleanTestClass = new BooleanTestClass();
        booleanTestClass.setTwo(true);
        assertThrows(BadConstraintException.class, () ->
                processor.validate(booleanTestClass.getClass().getField("two"), booleanTestClass));
    }

}