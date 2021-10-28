package org.example.validators;

import org.example.annotations.Regex;

import java.lang.reflect.Field;

public class ValidateRegexField <TClass> implements FieldValidator {
    Field field;
    TClass object;

    public ValidateRegexField(Field field, TClass object) {
        this.field = field;
        this.object = object;
    }

    @Override
    public boolean isFieldValid() {
        field.setAccessible(true);
        String value;
        try {
            value = (String) field.get(object);
            return value.matches(field.getAnnotation(Regex.class).pattern());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getErrorMessage(){
        return field.getAnnotation(Regex.class).message();
    }

}
