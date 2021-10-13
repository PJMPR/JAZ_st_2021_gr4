package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {
    public List<Method> getPublicGetters(Class<?> clazz){

        Method[] allMethods = clazz.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();


        for(Method method: allMethods){
            if(Modifier.isPublic(method.getModifiers())) {
                if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                    if (method.getParameterCount() == 0) {
                        if (!method.getReturnType().getSimpleName().equals("void")){
                            getters.add(method);
                        }
                    }
                }
            }
        }

        return getters;

    }


    public List<Method> getPublicSetters(Class<?> clazz){

        Method[] allMethods = clazz.getDeclaredMethods();
        List<Method> setters = new ArrayList<>();


        for(Method method: allMethods){
            if(Modifier.isPublic(method.getModifiers())) {
                if (method.getName().startsWith("set")) {
                    if (method.getParameterCount() == 1) {
                        if (method.getReturnType().getSimpleName().equals("void")){
                            setters.add(method);
                        }
                    }
                }
            }
        }

        return setters;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
