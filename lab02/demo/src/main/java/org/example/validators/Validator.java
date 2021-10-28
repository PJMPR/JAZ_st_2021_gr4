package org.example.validators;

import java.lang.reflect.Field;

public class Validator {
    public <TClass> ValidationResult validate(TClass object){
        ValidationResult resultObject = new ValidationResult();
        resultObject.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                ValidationField validationResult = new ValidationField(field,field.get(object));
                if(!validationResult.isValid()){
                    resultObject.getNotValidFields().put(field.getName(), validationResult.getListOfErrors());
                }
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        boolean ifObjectIsValid = resultObject.getNotValidFields().isEmpty();

        resultObject.setValid(ifObjectIsValid);
        return resultObject;
    }
}