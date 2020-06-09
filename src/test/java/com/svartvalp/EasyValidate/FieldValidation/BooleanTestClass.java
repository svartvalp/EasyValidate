package com.svartvalp.EasyValidate.FieldValidation;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

public class BooleanTestClass {
    @AssertFalse
    public boolean one;
    @AssertFalse
    @AssertTrue
    public boolean two;

    public void setOne(boolean one) {
        this.one = one;
    }

    public boolean isOne() {
        return one;
    }

    public boolean isTwo() {
        return two;
    }

    public void setTwo(boolean two) {
        this.two = two;
    }
}
