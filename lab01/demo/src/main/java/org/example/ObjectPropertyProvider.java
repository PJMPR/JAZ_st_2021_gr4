package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        List<Method> getters = new ArrayList<>();
        for (Method method : methods) {
            if(Modifier.toString(method.getModifiers()).equals("public")
                    && (method.getName().startsWith("get") || method.getName().startsWith("is"))
                    && !method.getReturnType().toGenericString().equals("void")
                    && method.getParameterTypes().length == 0
            ){
                getters.add(method);
            }
        }
        return getters;

    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        List<Method> getters = new ArrayList<>();
        for (Method method : methods) {
            if(Modifier.toString(method.getModifiers()).equals("public")
                    && (method.getName().startsWith("set"))
                    && method.getReturnType().toGenericString().equals("void")
                    && method.getParameterTypes().length == 1
            ){
                getters.add(method);
            }
        }
        return getters;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<Field> properties = new ArrayList<>();
        List<String> setters = getPublicSetters(clazz)
                .stream().map(x->x.getName().replaceAll("set", "").toLowerCase()).toList();

        List<String> getters = getPublicGetters(clazz)
                .stream().map(x->x.getName().replaceAll("get", "").replaceAll("is", "").toLowerCase()).toList();

        for(Field field : fields){
            if(getters.contains(field.getName().replaceAll("is", "").toLowerCase().toLowerCase()) || setters.contains(field.getName().toLowerCase())){
                properties.add(field);
            }
        }

        return properties;

    }


}
