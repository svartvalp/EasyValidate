package com.svartvalp.EasyValidate.FieldValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestSubClass extends TestClass {
    @Size(min = 2, max = 7)
    @NotNull
    public int[] five;

    public int[] getFive() {
        return five;
    }

    public void setFive(int[] five) {
        this.five = five;
    }
}
