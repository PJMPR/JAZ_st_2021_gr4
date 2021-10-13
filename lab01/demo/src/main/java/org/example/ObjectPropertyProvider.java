package org.example;


import javax.security.auth.Subject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        Method[] methods = clazz.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();

        for(Method method : methods){
            if(isGetter(method)){
                getters.add(method);
//                System.out.println(method);
            }
        }

        return getters;

    }

    public static boolean isGetter(Method method){
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        if(!(method.getName().startsWith("get") || method.getName().startsWith("is"))) return false;
        if(!(Modifier.toString(method.getModifiers()).equals("public"))) return false;
        return true;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        List<Method> setters = new ArrayList<>();

        for(Method method : methods){
            if(isSetter(method)){
                setters.add(method);
//                System.out.println(method);
            };
        }
        return setters;

    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        if(!(void.class.equals(method.getReturnType()))) return false;
        return true;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        Field[] fields   = clazz.getFields();
        Method[] methods = clazz.getMethods();
        List<Method> getters_setters = new ArrayList<>();
        List<Field> output = new ArrayList<>();

            for(Method method : methods){
                if(isSetter(method) ){
                    getters_setters.add(method);
//                    System.out.println(method);
                }
                else if(isGetter(method) ){
                    getters_setters.add(method);
//                    System.out.println(method);
                }
            }
            for(Field field : fields){
                if(hasGetterSetter(field, getters_setters) ){
                    output.add(field);
                }
            }
        return output;
    }

    public static boolean hasGetterSetter(Field field, List<Method> getters_setters){
        for (Method method : getters_setters) {
            if(field.getName().toLowerCase().endsWith(method.getName().toLowerCase())) {
                System.out.println(field.getName());
                return true;
            }
            else System.out.println(field.getName());
        }
        return false;
    }

}
