package org.example.validators.rules;

import org.example.annotations.Regex;

import java.lang.reflect.Field;

public class RegexValidationRule extends ValidationRule{
    @Override
    protected String message(Field field) {
        return field.getAnnotation(Regex.class).message();
    }

    @Override
    protected boolean checkField(Field field, Object validatedObject) throws IllegalAccessException {
        String value = (String) field.get(validatedObject);
        Regex regex =field.getAnnotation(Regex.class);
        return value.matches(regex.pattern());
    }

    @Override
    protected boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(Regex.class);
    }
}
