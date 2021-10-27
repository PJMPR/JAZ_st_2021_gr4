package org.example.validators;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){
       Field[] fieldList = object.getClass().getDeclaredFields();
       for (Field field: fieldList){
           System.out.println(Arrays.toString(field.getAnnotations()));
       }


        return null;
    }
}
