package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidationField {
    private List<String> listOfErrors;
    private Field field;
    private Object fieldValue;

    public ValidationField(Field fieldArg, Object value){
        fieldValue = value;
        field = fieldArg;
        listOfErrors = new ArrayList<>();
    }

    private boolean fieldEqualsNull(){
        if(fieldValue == null)
            return true;
        else
            return false;
    }

    private boolean isEmailNotValid(){
        String stringifyField = fieldValue.toString();
        if(stringifyField.matches(field.getAnnotation(Regex.class).pattern())){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean isNumberNotInRange(){
        int num = (int) fieldValue;
        boolean returnValue = false;
        if( num <= field.getAnnotation(Range.class).min() ) {
            returnValue = true;
        }
        else if (num >= field.getAnnotation(Range.class).max()) {
            returnValue = true;
        }
        return returnValue;
    }

    public List<String> getListOfErrors() {
        return listOfErrors;
    }

    public boolean isValid() {
        for (Annotation annotation: field.getDeclaredAnnotations()) {
            String annotationString = annotation.annotationType().getSimpleName();
            switch (annotationString) {
                default -> throw new IllegalStateException("Unexpected state:" + annotation.annotationType());

                case "Regex" -> {
                    if (isEmailNotValid() == true) {
                        listOfErrors.add(field.getAnnotation(Regex.class).message());
                    }
                }
                case "Range" -> {
                    if (isNumberNotInRange() == true) {
                        listOfErrors.add(field.getAnnotation(Range.class).message());
                    }
                }
                case "NotNull" -> {
                    if (fieldEqualsNull() == true) {
                        listOfErrors.add(field.getAnnotation(NotNull.class).message());
                    }
                }
            }

        }
        if(listOfErrors.isEmpty()){
            return true;
        }
        else
            return false;
    }
}