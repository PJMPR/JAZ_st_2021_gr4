package org.example.validators;


import org.example.validators.rules.ICheckValidationRule;
import org.example.validators.rules.NotNullValidationRule;
import org.example.validators.rules.RangeValidationRule;
import org.example.validators.rules.RegexValidationRule;

import java.util.List;

public class Validator {

    List<ICheckValidationRule> rules = List.of(
            new NotNullValidationRule(),
            new RangeValidationRule(),
            new RegexValidationRule()
    );

    public <TClass> ValidationResult validate(TClass object){

        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);
        rules.stream().forEach(rule->rule.validate(result));
        return result;
    }
}
