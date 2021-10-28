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
        validationResult.setValidatedObject(object);


        for (Field field :
                fields) {

            List<String> nonValidFields = new ArrayList<String>();

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    System.out.println(field.get(object));
                    if (field.get(object) == null) {
                        nonValidFields.add(field.getAnnotation(NotNull.class).message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Regex.class)) {
                try {
                    field.setAccessible(true);
                    String pattern = field.getAnnotation(Regex.class).pattern();

                    if (!field.get(object).toString().matches(pattern)) {
                        nonValidFields.add(field.getAnnotation(Regex.class).message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Range.class)) {
                try {
                    field.setAccessible(true);
                    if ((Integer)field.get(object) > field.getAnnotation(Range.class).max() || (Integer)field.get(object) < field.getAnnotation(Range.class).min()) {
                        nonValidFields.add(field.getAnnotation(Range.class).message() + field.getAnnotation(Range.class).min() + "," + field.getAnnotation(Range.class).max() + "]");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (nonValidFields.size() > 0) validationResult.putToNotValidFields(field.getName(), nonValidFields);
            System.out.println(validationResult.getNotValidFields());
        }

        System.out.println(validationResult.getNotValidFields());
        validationResult.setValid(validationResult.getNotValidFields().size() == 0);
        return validationResult;
    }
}
