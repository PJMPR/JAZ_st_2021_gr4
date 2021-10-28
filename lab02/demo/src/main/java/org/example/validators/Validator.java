package org.example.validators;

import java.lang.reflect.Field;

public class Validator {
    public <TClass> ValidationResult validate(TClass object){
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                FieldToValidate fieldToValidate = new FieldToValidate(field,field.get(object));
                if(!fieldToValidate.isValid()){
                    validationResult.getNotValidFields().put(field.getName(), fieldToValidate.getErrors());
                }
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }

        boolean isObjectValid = validationResult.getNotValidFields().isEmpty();
        validationResult.setValid(isObjectValid);

        return validationResult;
    }
}
