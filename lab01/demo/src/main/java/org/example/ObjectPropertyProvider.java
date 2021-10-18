package org.example;


import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        List<Method> lista = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> lista2 = new ArrayList<>();

        for(Method metoda:lista){

            String c = metoda.getName();
            String b = c.substring(0,3);
            String d = c.substring(0,2);
            int parameter = metoda.getParameterCount();
            String annotatedType = metoda.getAnnotatedReturnType().getType().getTypeName();

            if(parameter==0 && d.equals("is") && b.equals("get") && Modifier.isPublic(metoda.getModifiers()) && !annotatedType.equals("void")){
                lista2.add(metoda);
            }
        }
        return lista2;

    }


    public List<Method> getPublicSetters(Class<?> clazz){

        List<Method> lista = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> lista2 = new ArrayList<>();
        for(Method metoda:lista){
            String annotatedType = metoda.getAnnotatedReturnType().getType().getTypeName();
            String c = metoda.getName();
            String b = c.substring(0,3);
            int parameter = metoda.getParameterCount();
            if(Modifier.isPublic(metoda.getModifiers()) && annotatedType.equals("void") && parameter==1){
                if(b.equals("set") || b.contains("Set")){
                    lista2.add(metoda);
                }
            }
        }
        return lista2;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();
    }
}
