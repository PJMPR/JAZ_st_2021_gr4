package org.example.validators;

import java.lang.reflect.Field;

public class ValidatorFacade <TClass> {
    TClass object;
    ValidationResult validationResult;
    Field[] fields;

    public ValidatorFacade(TClass object) {
        this.object = object;
        validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);

        fields = object.getClass().getDeclaredFields();
    }

    public ValidationResult validate() {
        for (Field field: fields){
            FieldValidationResult fieldResult = new FieldValidationResult(object, field);

            if(!fieldResult.isFieldValid()) {
                validationResult.addNotValidField(field.getName(), fieldResult.getErrorMessages());
                validationResult.setValid(false);
            }

        }
        return validationResult;
    }
}
