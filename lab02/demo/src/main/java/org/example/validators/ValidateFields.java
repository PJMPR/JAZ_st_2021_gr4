package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidateFields {

    private final Object fieldValue;
    private final Field field;
    private final List<String> errors;

    public ValidateFields(Field fieldValidate, Object objectValidate){
        fieldValue = objectValidate;
        field = fieldValidate;
        errors = new ArrayList<String>();
    }

    public List<String> getErrorsList(){
        return errors;
    }

    private boolean isFieldNull(){
        return(fieldValue==null);
    }

    private boolean isFieldRegexValid(){
        return((fieldValue.toString()).matches(field.getAnnotation(Regex.class).pattern()));
    }

    private boolean isFieldInRange(){
        int number = (int) fieldValue;
        return(number<=field.getAnnotation(Range.class).max() && number>=field.getAnnotation(Range.class).min());
    }

    public boolean checkForErrors(){
        for(Annotation annotation: field.getDeclaredAnnotations()) {
            String checkAnnotations = annotation.annotationType().getSimpleName();
            switch (checkAnnotations) {
                case "NotNull" -> {
                    if (isFieldNull())
                        errors.add(field.getAnnotation(NotNull.class).message());
                }
                case "Regex" -> {
                    if (!isFieldRegexValid())
                        errors.add(field.getAnnotation(Regex.class).message());
                }
                case "Range" -> {
                    if (!isFieldInRange())
                        errors.add(field.getAnnotation(Range.class).message());
                }
            }
        }
        return (errors.isEmpty());
    }
}
