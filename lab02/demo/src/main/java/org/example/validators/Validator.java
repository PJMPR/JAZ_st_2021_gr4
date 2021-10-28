package org.example.validators;


import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){

        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);

        for(Field field : object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                ValidateFields validateFields = new ValidateFields(field, field.get(object));
                if(!validateFields.checkForErrors())
                    validationResult.getNotValidFields().put(field.getName(), validateFields.getErrorsList());
            }
                catch (Exception e) {
                e.printStackTrace();
            }
        }

        return validationResult;
    }
}
