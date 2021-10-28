package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldToValidate {
    private Field field;
    private Object value;
    private List<String> errors;

    public FieldToValidate(Field field, Object value){
        this.field = field;
        this.value = value;
        errors = new ArrayList<>();
    }

    public List<String> getErrors(){
        return errors;
    }

    private boolean isFieldNull(){
        return value==null;
    }

    private boolean isEmailValid(){
        return value.toString().matches(field.getAnnotation(Regex.class).pattern());
    }

    private boolean isNumberInRange(){
        int number = (int) value;
        return number <= field.getAnnotation(Range.class).max() && number >= field.getAnnotation(Range.class).min();
    }

    public boolean isValid() {
        for (Annotation annotation: field.getDeclaredAnnotations()) {
            String annotationString = annotation.annotationType().getSimpleName();
            switch (annotationString) {
                case "NotNull" -> {
                    if (isFieldNull())
                        errors.add(field.getAnnotation(NotNull.class).message());
                }
                case "Regex" -> {
                    if (!isEmailValid())
                        errors.add(field.getAnnotation(Regex.class).message());
                }
                case "Range" -> {
                    if (!isNumberInRange())
                        errors.add(field.getAnnotation(Range.class).message());
                }
            }

        }
        return errors.isEmpty();
    }
}
