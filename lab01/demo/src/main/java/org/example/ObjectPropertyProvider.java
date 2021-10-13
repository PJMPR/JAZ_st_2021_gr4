package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ObjectPropertyProvider {

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get")){
            return false;
        }
        if(method.getParameterTypes().length != 0){
            return false;
        }
        if(void.class.equals(method.getReturnType())){
            return false;
        }
        if(!Modifier.isPublic(method.getModifiers())){
            return false;
        }
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) {
            return false;
        }
        if(method.getParameterTypes().length != 1) {
            return false;
        }

        if(!Modifier.isPublic(method.getModifiers())){
            return false;
        }

        if(!void.class.equals(method.getReturnType())){
            return false;
        }
        return true;
    }

    public List<Method> getPublicGetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> passedMethods = new ArrayList<Method>();
        for(Method method : methods){
            if(isGetter(method)){
                passedMethods.add(method);
            }
        }
        System.out.println(Arrays.toString(passedMethods.toArray()));
        return passedMethods;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> passedMethods = new ArrayList<Method>();
        for(Method method : methods){
            if(isSetter(method)){
                passedMethods.add(method);
            }
        }
        System.out.println(Arrays.toString(passedMethods.toArray()));
        return passedMethods;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){
        Field[] fields = clazz.getFields();

    }



}