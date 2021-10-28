package org.example.validators;
import java.lang.reflect.Field;


public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);

        Field[] fieldList = object.getClass().getDeclaredFields();

       for (Field field: fieldList){
           FieldValidationResult fieldResult = new FieldValidationResult(object, field);

           if(!fieldResult.isFieldValid()) {
               validationResult.addNotValidField(field.getName(), fieldResult.getErrorMessages());
               validationResult.setValid(false);
           }

       }
       return validationResult;
    }
}
