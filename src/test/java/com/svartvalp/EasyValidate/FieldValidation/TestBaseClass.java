package com.svartvalp.EasyValidate.FieldValidation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;

public class TestBaseClass {
    @Min(20)
    @Max(60)
    public int one;
    @Min(20)
    public BigInteger two;

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public BigInteger getTwo() {
        return two;
    }

    public void setTwo(BigInteger two) {
        this.two = two;
    }
}
