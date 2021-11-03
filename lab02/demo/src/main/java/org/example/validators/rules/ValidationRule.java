package org.example.validators.rules;

import org.example.annotations.NotNull;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class ValidationRule implements ICheckValidationRule{

    public void validate(ValidationResult validationResult){
        List<Field> fields = Arrays.stream(validationResult
                        .getValidatedObject()
                        .getClass()
                        .getDeclaredFields())
                .toList();
        fields.stream().filter(field->hasAnnotation(field))
                .filter(field -> !passRule(field, validationResult))
                .forEach(field ->
                        {
                            validationResult.setValid(false);
                            getMessage(field, validationResult.getNotValidFields());
                }
                );
    }

    private void getMessage(Field field, Map<String, List<String>> notValidFields) {
        String fieldName  = field.getName();
        if(!notValidFields.containsKey(fieldName))
            notValidFields.put(fieldName, new ArrayList<>());
        notValidFields.get(fieldName).add(message(field));
    }

    private boolean passRule(Field field, ValidationResult validationResult) {
        try {
            field.setAccessible(true);
            return checkField(field, validationResult.getValidatedObject());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract String message(Field field) ;

    protected abstract boolean checkField(Field field, Object validatedObject) throws IllegalAccessException ;

    protected abstract boolean hasAnnotation(Field field);
}
