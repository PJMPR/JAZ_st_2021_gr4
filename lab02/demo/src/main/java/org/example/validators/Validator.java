package org.example.validators;
import java.lang.reflect.Field;


public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        return new ValidatorFacade(object).validate();
    }
}
