package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldValidationResult {
    FieldValidator fieldValidator;
    private final Object object;
    private final Field field;
    private final List<String> errorMessages;
    private boolean isFieldValid;


    public FieldValidationResult(Object object, Field field) {
        this.field = field;
        this.object = object;
        this.errorMessages = new ArrayList<>();
        this.validate();
    }

    public void validate(){
        if(field.isAnnotationPresent(NotNull.class)){
            fieldValidator = new ValidateNotNullField(field, object);
            if(!fieldValidator.isFieldValid()){
                errorMessages.add(fieldValidator.getErrorMessage());
            }

        }
        if(field.isAnnotationPresent(Range.class)){
            fieldValidator = new ValidateRangeField(field, object);
            if(!fieldValidator.isFieldValid()) {
                errorMessages.add(fieldValidator.getErrorMessage());
            }

        }
        if(field.isAnnotationPresent(Regex.class)){
            fieldValidator = new ValidateRegexField(field, object);
            if(!fieldValidator.isFieldValid()){
                errorMessages.add(fieldValidator.getErrorMessage());
            }

        }
        isFieldValid = errorMessages.size() == 0;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public boolean isFieldValid() {
        return isFieldValid;
    }
}
