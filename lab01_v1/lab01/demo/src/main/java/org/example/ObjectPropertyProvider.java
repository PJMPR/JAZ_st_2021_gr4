package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class ObjectPropertyProvider implements IObjectPropertyProvider {

    @Override
    public List<Method> getPublicGetters(Class<?> clazz){

        /*List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method>  results = new ArrayList<>();

        for (Method m : methods) {
            if(new SimpleMethod(m).isGetter())
                results.add(m);
        }*/

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m->new SimpleMethod(m).isGetter())
                .toList();
    }


    @Override
    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m->new SimpleMethod(m).isSetter())
                .toList();
    }


    @Override
    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        Stream<Method> propsStream = getMethodStream(clazz);
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> nameMatch(propsStream, field))
                .toList();
    }

    private Stream<Method> getMethodStream(Class<?> clazz) {
        List<Method> props = getPublicGetters(clazz);
        props.addAll(getPublicSetters(clazz));
        Stream<Method> propsStream = props.stream();
        return propsStream;
    }

    private boolean nameMatch(Stream<Method> propsStream, Field field) {
        return propsStream.anyMatch(prop->
                new SimpleMethod(prop)
                        .getFieldName()
                        .equalsIgnoreCase(field.getName()));
    }
}
