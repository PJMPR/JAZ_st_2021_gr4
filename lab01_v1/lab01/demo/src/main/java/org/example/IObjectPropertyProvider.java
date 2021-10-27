package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface IObjectPropertyProvider {
    List<Method> getPublicGetters(Class<?> clazz);

    List<Method> getPublicSetters(Class<?> clazz);

    List<Field> getFieldsForPublicProperties(Class<?> clazz);
}
