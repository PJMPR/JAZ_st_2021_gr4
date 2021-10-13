package org.example;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Class<?> clazz = Car.class;
        List<Method> getters = getPublicGetters(clazz);
        List<Method> setters = getPublicSetters(clazz);
        List<Method> both = getPublicFields(clazz);
        System.out.println(getters);
        System.out.println(setters);
        System.out.println(both);
    }

    public static List<Method> getPublicGetters(Class<?> clazz) {
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List getters = new ArrayList();
        //List<Method> getters = Collections.<Method>emptyList();
        for (int i=0;i<methods.size();i++){

            if (Modifier.isPublic(methods.get(i).getModifiers()))
            {
                String name=(methods.get(i)).getName();
                if (name.startsWith("get")){
                    //System.out.println(name);
                    getters.add(methods.get(i));
                }
            }
        }
        return getters;

    }
    public static List<Method> getPublicSetters(Class<?> clazz){

        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List getters = new ArrayList();
        //List<Method> getters = Collections.<Method>emptyList();
        for (int i=0;i<methods.size();i++){

            if (Modifier.isPublic(methods.get(i).getModifiers()))
            {
                String name=(methods.get(i)).getName();
                if (name.startsWith("set")){
                    //System.out.println(name);
                    getters.add(methods.get(i));
                }
            }

        }
        return getters;

    }
    public static List<Method> getPublicFields(Class<?> clazz){

        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List getters = new ArrayList();
        //List<Method> getters = Collections.<Method>emptyList();
        for (int i=0;i<methods.size();i++){

            if (Modifier.isPublic(methods.get(i).getModifiers()))
            {
                String name=(methods.get(i)).getName();
                if (name.startsWith("get") || name.startsWith("set")){
                    //System.out.println(name);
                    getters.add(methods.get(i));
                }
            }



        }
        return getters;
    }




}

class Car {
    int wiek;
    int moc;
    String marka;
    //Constructor
    public Car(int wiek, int moc, String marka) {
        this.wiek = wiek;
        this.moc = moc;
        this.marka = marka;
    }

    //Getters
    public int getWiek() {
        return wiek;
    }

    public int getMoc() {
        return moc;
    }

    public String getMarka() {
        return marka;
    }

    //Setters
    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setMoc(int moc) {
        this.moc = moc;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }


}
