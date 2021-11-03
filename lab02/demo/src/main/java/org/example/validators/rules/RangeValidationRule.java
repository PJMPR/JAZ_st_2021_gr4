package org.example.validators.rules;

import org.example.annotations.Range;

import java.lang.reflect.Field;

public class RangeValidationRule extends ValidationRule{
    @Override
    protected String message(Field field) {
        Range range = field.getAnnotation(Range.class);
        return range.message().formatted(range.min(), range.max());
    }

    @Override
    protected boolean checkField(Field field, Object validatedObject) throws IllegalAccessException {

        Range range = field.getAnnotation(Range.class);
        Number number = (Number) field.get(validatedObject);
        return range.min()<number.doubleValue()
                && number.doubleValue()<range.max();
    }

    @Override
    protected boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(Range.class);
    }
}
