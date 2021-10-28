package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validations {
    private List<String> errorList;
    private Field field;
    private Object fieldVal;

    public Validations(Field field1, Object val){
        fieldVal = val;
        field = field1;
        errorList = new ArrayList<>();
    }

    private boolean checkNull() {
        if(fieldVal == null) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkRange() {
        int number = (int) fieldVal;
        boolean flag = false;
        if(number <= field.getAnnotation(Range.class).min() ) {
            flag = true;
        }
        if (number >= field.getAnnotation(Range.class).max()) {
            flag = true;
        }
        return flag;
    }

    private boolean checkEmail() {
        if(fieldVal.toString().matches(field.getAnnotation(Regex.class).pattern())){
            return false;
        }
        else {
            return true;
        }
    }

    public List<String> getListOfErrors() {
        return errorList;
    }

    public boolean isValid() {
        for (Annotation annotation: field.getDeclaredAnnotations()) {
            String ann = annotation.annotationType().getSimpleName();

            if (ann.equals("Regex")) {
                if (checkEmail() == true) {
                        errorList.add(field.getAnnotation(Regex.class).message());
                }
            }
            else if (ann.equals("Range")) {
                if (checkRange() == true) {
                        errorList.add(field.getAnnotation(Range.class).message());
                }
            }
            else if (ann.equals("NotNull") ) {
                if (checkNull() == true) {
                        errorList.add(field.getAnnotation(NotNull.class).message());
                }
            }
            else {
                throw new IllegalStateException("Unexpected state:" + annotation.annotationType());
            }

        }
        if(errorList.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }
}