package com.svartvalp.EasyValidate.FieldValidation;

import java.lang.reflect.Field;

public interface FieldResolver {
    public Field[] getAllFields(Object obj);
}
