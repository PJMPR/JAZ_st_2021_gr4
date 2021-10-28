package org.example.validators;
import java.lang.reflect.Field;
import java.util.*;


public class Validator {

    public <TClass> ValidationResult validate(TClass object) {

        List<String> errorMessages = new ArrayList<>();

        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);
        FieldValidators fieldValidators = new FieldValidators();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fieldValidators.fieldNotNUll(object, errorMessages, validationResult, field);
            fieldValidators.fieldInRange(object, errorMessages, validationResult, field);
            fieldValidators.fieldRegex(object, errorMessages, validationResult, field);

            if (!errorMessages.isEmpty()) {
                validationResult.getNotValidFields().put(field.getName(), new ArrayList<>(errorMessages));
                errorMessages.clear();
            }
        }
        return validationResult;
    }

}