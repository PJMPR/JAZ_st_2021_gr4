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

        List<Method> passed = new ArrayList<Method>();
        for(Method method : methods){
            if(isGetter(method)) {
                passed.add(method);
            }
        }
        return passed;
    }
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get") && !method.getName().startsWith("is"))      {return false;}
        if(method.getParameterTypes().length != 0)   {return false;}
        if(void.class.equals(method.getReturnType())) {return false;}
        if(!Modifier.isPublic(method.getModifiers())) {return false;}
        return true;
        }

    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();

        List<Method> passed = new ArrayList<Method>();
        for(Method method : methods){
            if(isSetter(method)) {
                passed.add(method);
            }
        }
        return passed;
    }
    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set"))      {return false;}
        if(method.getParameterTypes().length != 1)   {return false;}
        if(!Modifier.isPublic(method.getModifiers())) {return false;}
        if(!void.class.equals(method.getReturnType())) {return false;}
        return true;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
