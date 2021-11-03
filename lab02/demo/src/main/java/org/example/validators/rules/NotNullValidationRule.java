package org.example.validators.rules;

import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class NotNullValidationRule extends ValidationRule {

    protected String message(Field field) {
        return field.getAnnotation(NotNull.class).message();
    }

    protected boolean checkField(Field field, Object validatedObject) throws IllegalAccessException {
        return field.get(validatedObject)!=null;
    }

    protected boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(NotNull.class);
    }

}
