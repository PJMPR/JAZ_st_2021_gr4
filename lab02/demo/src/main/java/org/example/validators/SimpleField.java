package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;

public class SimpleField {
    Field field;


    public SimpleField(Field field) {
        this.field = field;
    }
    public String getName() {
        return (String)field.getName();
    }

    //Check Types or Values
    public boolean isInteger(){
        return field.getType().equals(int.class);
    }

    public boolean isString(){
        return field.getType().equals(String.class);
    }

    public <TClass> boolean isNull(TClass object) throws IllegalAccessException {
        return field.get(object)==null;
    }


    //Return field value
    public <TClass> int integerFieldValue(TClass object) throws IllegalAccessException {
        return (int)field.get(object);
    }

    public <TClass> String stringFieldValue(TClass object) throws IllegalAccessException {
        return (String)field.get(object);
    }


    //Check if Annotation is present
    public boolean notNullAnnotationPresent(){
        return field.isAnnotationPresent(NotNull.class);
    }

    public boolean regexAnnotationPresent(){
        return field.isAnnotationPresent(Regex.class);
    }

    public boolean rangeAnnotationPresent(){
        return field.isAnnotationPresent(Range.class);
    }


    //Get Annotations Messages
    public String getNotNullAnnotationMessage() {
        return field.getAnnotation(NotNull.class).message();
    }

    public String getRegexAnnotationMessage() {
        return field.getAnnotation(Regex.class).message();
    }

    public String getRangeAnnotationMessage() {
        return field.getAnnotation(Range.class).message();
    }


    //Get extremes & out of range
    public int minimalRange(){
        return field.getAnnotation(Range.class).min();
    }

    public int maximalRange(){
        return field.getAnnotation(Range.class).max();
    }

    public <TClass> boolean fieldValueOutOfRange(TClass object) throws IllegalAccessException {
        return (minimalRange()>integerFieldValue(object) || maximalRange()<integerFieldValue(object));
    }


    //Get regex pattern & not regex valid
    public String regexPattern(){
        return field.getAnnotation(Regex.class).pattern();
    }

    private <TClass> boolean isNotRegexValid(TClass object) throws IllegalAccessException {
        return !stringFieldValue(object).matches(regexPattern());
    }


    //Final Methods
    public <TClass> boolean valueIsNull(TClass object) throws IllegalAccessException {
        if (notNullAnnotationPresent()){
            return isNull(object);
        }
        else return false;
    }

    public <TClass> boolean valueIsNotRegexValid(TClass object) throws IllegalAccessException {
        if (isString() && regexAnnotationPresent()){
            return isNotRegexValid(object);
        }
        else return false;

    }

    public <TClass> boolean valueIsOutOfRange(TClass object) throws IllegalAccessException {
        if (isInteger() && rangeAnnotationPresent()){
            return fieldValueOutOfRange(object);
        }
        else return false;
    }

}
