package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public boolean isGetter(Method method) {
        if ((!method.getName().startsWith("get") && (!method.getName().startsWith("is"))) || method.getParameterCount() != 0 || method.getReturnType().equals(Void.TYPE) || !Modifier.isPublic(method.getModifiers())) {
            return false;
        } else {
            return true;
        }
    }
    public boolean isSetter(Method method) {
        if (!method.getName().startsWith("set") || method.getParameterCount() != 1 || !method.getReturnType().equals(Void.TYPE) || !Modifier.isPublic(method.getModifiers())) {
            return false;
        } else {
            return true;
        }
    }

    public List<Method> getPublicGetters(Class<?> clazz){
        List methodsInitial = Arrays.stream(clazz.getDeclaredMethods()).toList();
        ArrayList methodsOutput = new ArrayList();
        //Method[] methods = clazz.getMethods();
        for (int i = 0; i < methodsInitial.size(); i++) {
            if (isGetter((Method) methodsInitial.get(i))) {
                methodsOutput.add(methodsInitial.get(i));
            }
        }
        return methodsOutput;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        List methodsInitial = Arrays.stream(clazz.getDeclaredMethods()).toList();
        ArrayList methodsOutput = new ArrayList();
        //Method[] methods = clazz.getMethods();
        for (int i = 0; i < methodsInitial.size(); i++) {
            if (isSetter((Method) methodsInitial.get(i))) {
                methodsOutput.add(methodsInitial.get(i));
            }
        }
        return methodsOutput;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
