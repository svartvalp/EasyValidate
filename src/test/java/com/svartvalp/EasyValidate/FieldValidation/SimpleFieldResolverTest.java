package com.svartvalp.EasyValidate.FieldValidation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleFieldResolverTest {

    @Test
    public void getAllFieldsTest() {
        TestSubClass test = new TestSubClass();
        assertEquals(5, new SimpleFieldResolver().getAllFields(test).length);
        assertEquals("five", new  SimpleFieldResolver().getAllFields(test)[0].getName());
    }

}