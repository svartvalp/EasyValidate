package com.svartvalp.EasyValidate.FieldValidation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SimpleFieldResolver implements FieldResolver {
    @Override
    public Field[] getAllFields(Object obj) {
        Class<?> currentClass = obj.getClass();
        List<Field> allFields = new ArrayList<>();
        while(currentClass != null) {
            allFields.addAll(getClassFields(currentClass));
            currentClass = currentClass.getSuperclass();
        }
        return allFields.toArray(Field[]::new);
    }


    private List<Field> getClassFields(Class<?> c) {
        return List.of(c.getDeclaredFields());
    }
}
