package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println("getter: " + method);
        }
        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get") && !method.getName().startsWith("is"))      {return false;}
        if(method.getParameterTypes().length != 0)   {return false;}
        if(void.class.equals(method.getReturnType())) {return false;}
        return true;
        }

    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
