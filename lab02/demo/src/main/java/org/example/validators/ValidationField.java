package org.example.validators;
import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidationField {
    final List<String> errorsList;
    final Field field;
    final Object fieldValue;

    public ValidationField(Field fieldArgs, Object value){
        fieldValue = value;
        field = fieldArgs;
        errorsList = new ArrayList<>();
    }
    public boolean numberNotInRange(){
        int num = (int) fieldValue;
        return num <= field.getAnnotation(Range.class).min() || num >= field.getAnnotation(Range.class).max();
    }
    public List<String> getErrorsList() {
        return errorsList;
    }
    public  boolean emailNotValid(){
        String stringifyField = fieldValue.toString();
        return !stringifyField.matches(field.getAnnotation(Regex.class).pattern());
    }
    public boolean isValid() {
        if (field.isAnnotationPresent(Regex.class) && emailNotValid()) {
            errorsList.add(field.getAnnotation(Regex.class).message());}
        else if ((field.isAnnotationPresent(Range.class)) && numberNotInRange()) {
            errorsList.add(field.getAnnotation(Range.class).message());}
        else if ((field.isAnnotationPresent(NotNull.class)) && fieldValue == null) {
            errorsList.add(field.getAnnotation(NotNull.class).message());}
        return errorsList.isEmpty();
    }
}
