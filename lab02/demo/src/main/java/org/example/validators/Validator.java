package org.example.validators;

import java.lang.reflect.Field;

public class Validator {
    public <TClass> ValidationResult validate(TClass object){
        ValidationResult resultObj = new ValidationResult();
        resultObj.setValidatedObject(object);
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Validations result = new Validations(field,field.get(object));
                if(result.isValid()) {
                    continue;
                }
                else {
                    resultObj.getNotValidFields().put(field.getName(), result.getListOfErrors());
                }
            }
        }
        catch (IllegalAccessException e){}

        resultObj.setValid(resultObj.getNotValidFields().isEmpty());

        return resultObj;
    }
}