package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){

        //get all fields
        Field[] fields = object.getClass().getDeclaredFields();
        ValidationResult validationResult = new ValidationResult();
        System.out.println(Arrays.toString(fields));
        for (Field field :
                fields) {

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(object) == null) {
                        List<String> nonValidFields = new ArrayList<String>();
                        nonValidFields.add(field.getAnnotation(NotNull.class).message());
                        validationResult.putToNotValidFields(field.getName(), nonValidFields);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Regex.class)) {
                try {
                    field.setAccessible(true);
                    String pattern = field.getAnnotation(Regex.class).pattern();
                    if (object.getEmail().matches(pattern)) {
                        List<String> nonValidFields = new ArrayList<String>();
                        nonValidFields.add(field.getAnnotation(Regex.class).message());
                        validationResult.putToNotValidFields(field.getName(), nonValidFields);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Range.class)) {
                try {
                    field.setAccessible(true);
                    if (object.getNumber() < field.getAnnotation(Range.class).max() && object.getNumber() > field.getAnnotation(Range.class).min()) {
                        List<String> nonValidFields = new ArrayList<String>();
                        nonValidFields.add(field.getAnnotation(NotNull.class).message());
                        validationResult.putToNotValidFields(field.getName(), nonValidFields);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(validationResult.getNotValidFields());
        return validationResult;
    }
}
