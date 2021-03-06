package com.svartvalp.EasyValidate.FieldValidation;

import javax.validation.constraints.*;

public class TestClass extends TestBaseClass {
    @Size(min = 1)
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Email
    private String three;
    @AssertTrue
    private Boolean four;

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public Boolean getFour() {
        return four;
    }

    public void setFour(Boolean four) {
        this.four = four;
    }
}
