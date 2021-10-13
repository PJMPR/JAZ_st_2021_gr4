package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            if(method.getName().startsWith("get")&&method.getName().startsWith("set") && !void.class.equals(method.getReturnType()) && (method.getParameterTypes().length == 0) ){
                System.out.println("getter: " + method);
            }
        }
        return Arrays.stream(clazz.getDeclaredMethods()).toList();

    }


    public List<Method> getPublicSetters(Class<?> clazz){

        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            if(method.getName().startsWith("set") && method.getParameterTypes().length != 1 && void.class.equals(method.getReturnType()) && method.getParameterTypes().length == 1)
                System.out.println("setter: " + method);
        }

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            if(method.getName().startsWith("get")&&method.getName().startsWith("set") && !void.class.equals(method.getReturnType()) && (method.getParameterTypes().length == 0) ){
                System.out.println("getter: " + method);
            }
            if(method.getName().startsWith("set") && method.getParameterTypes().length != 1 && void.class.equals(method.getReturnType()) && method.getParameterTypes().length == 1)
                System.out.println("setter: " + method);
        }

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
