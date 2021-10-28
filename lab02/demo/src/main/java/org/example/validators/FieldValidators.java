package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.List;

public class FieldValidators {
    public  <TClass> void fieldRegex(TClass object, List<String> errorMessages, ValidationResult validationResult, Field field) {
        if (field.isAnnotationPresent(Regex.class)) {
            try {
                if (!field.get(object).toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                    errorMessages.add(field.getAnnotation(Regex.class).message());
                    validationResult.setValid(false);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public  <TClass> void fieldInRange(TClass object, List<String> errorMessages, ValidationResult validationResult, Field field) {
        if (field.isAnnotationPresent(Range.class)) {
            try {
                if (field.getDeclaredAnnotation(Range.class).min() >= (Integer) field.get(object)
                        || field.getDeclaredAnnotation(Range.class).max() <= (Integer) field.get(object)) {
                    errorMessages.add(field.getAnnotation(Range.class).message());
                    validationResult.setValid(false);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public  <TClass> void fieldNotNUll(TClass object, List<String> errorMessages, ValidationResult validationResult, Field field) {
        if (field.isAnnotationPresent(NotNull.class)) {
            try {
                if (field.get(object) == null) {
                    errorMessages.add(field.getAnnotation(NotNull.class).message());
                    validationResult.setValid(false);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
