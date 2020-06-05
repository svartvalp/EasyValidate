package com.svartvalp.EasyValidate.FieldValidation;

import javax.validation.constraints.Size;

public class TestClass extends TestBaseClass {
    @Size(min = 1)
    private String three;
    private Boolean four;
}
