package org.example.validators;

import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class ValidateNotNullField <TClass> implements FieldValidator{
    Field field;
    TClass object;

    public ValidateNotNullField(Field field, TClass object) {
        this.field = field;
        this.object = object;
    }

    @Override
    public boolean isFieldValid() {
        field.setAccessible(true);
        try {
            return field.get(object) != null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public String getErrorMessage(){
        return field.getAnnotation(NotNull.class).message();
    }
}
