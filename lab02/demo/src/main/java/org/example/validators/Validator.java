package org.example.validators;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {

        ValidationResult result = new ValidationResult();
        Map<String, List<String>> notValidFields = new HashMap() {};

        Field[] fields = createFieldsList(object);

        setValidatedObjectAndValidationToTrue(object,result);

        for (Field field : fields) {

            field.setAccessible(true);
            SimpleField f = new SimpleField(field);
            List<String> messages = new ArrayList<String>();

            if(f.valueIsNull(object)){
                result.setValid(false);
                messages.add(f.getNotNullAnnotationMessage());
            }

            if(f.valueIsNotRegexValid(object)){
                result.setValid(false);
                messages.add(f.getRegexAnnotationMessage());
            }

            if(f.valueIsOutOfRange(object)){
                result.setValid(false);
                messages.add(f.getRangeAnnotationMessage());
            }


            if(messagesNotEmpty(messages)){
                addElementsToMap(f.getName(),messages,notValidFields);
            }
        }

        result.setNotValidFields(notValidFields);
        return result;
    }

    private <TClass> void setValidatedObjectAndValidationToTrue(TClass object, ValidationResult result) {
        result.setValidatedObject(object);
        result.setValid(true);
    }

    private <TClass> Field[] createFieldsList(TClass object) {
        return object.getClass().getDeclaredFields();
    }

    private void addElementsToMap(String name, List<String> messages, Map<String, List<String>> map) {
        map.put(name, messages);
    }

    private boolean messagesNotEmpty(List<String> messages) {
        return messages.size()>0;
    }

}





