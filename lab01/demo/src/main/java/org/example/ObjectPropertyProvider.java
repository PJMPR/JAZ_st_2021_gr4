package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> result = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                if (method.getParameterTypes().length == 0) {
                    if (!void.class.equals(method.getReturnType())) {
                        if (Modifier.isPublic(method.getModifiers())) {
                            System.out.println(method.getName());
                            result.add(method);
                        }
                    }
                }
            }
        }
        return result;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> result = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                if (method.getParameterTypes().length == 1) {
                    if (void.class.equals(method.getReturnType())) {
                        if (Modifier.isPublic(method.getModifiers())) {
                            System.out.println(method.getName());
                            result.add(method);
                        }
                    }
                }
            }
        }
        return result;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
