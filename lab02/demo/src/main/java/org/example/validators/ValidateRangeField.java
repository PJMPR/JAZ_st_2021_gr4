package org.example.validators;

import org.example.annotations.Range;

import java.lang.reflect.Field;


public class ValidateRangeField <TClass> implements FieldValidator{
    Field field;
    TClass object;

    public ValidateRangeField(Field field, TClass object) {
        this.field = field;
        this.object = object;
    }

    @Override
    public boolean isFieldValid() {
        field.setAccessible(true);
        try {
            return isValueInRange(fieldValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getErrorMessage(){
        return field.getAnnotation(Range.class).message()
                    + String.format("[%d,%d]", getMin(), getMax());
    }

    public int fieldValue() throws IllegalAccessException {
        return (Integer) field.get(object);
    }

    public boolean isValueInRange(int value){
        return getMin() < value && value < getMax();
    }

    public int getMin(){
        return field.getAnnotation(Range.class).min();
    }

    public int getMax(){
        return field.getAnnotation(Range.class).max();
    }


}
