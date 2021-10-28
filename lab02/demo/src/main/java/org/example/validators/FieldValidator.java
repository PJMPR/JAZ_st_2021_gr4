package org.example.validators;

public interface FieldValidator {
    boolean isFieldValid();
    String getErrorMessage();
}
