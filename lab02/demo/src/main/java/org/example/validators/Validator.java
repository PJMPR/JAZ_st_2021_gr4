package org.example.validators;
import java.lang.reflect.Field;


public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field: fields){
            FieldValidationResult fieldResult = new FieldValidationResult(object, field);

            if(!fieldResult.isFieldValid()) {
                validationResult.getNotValidFields().put(field.getName(), fieldResult.getErrorMessages());
                validationResult.setValid(false);
            }

        }
        return validationResult;

    }
}


