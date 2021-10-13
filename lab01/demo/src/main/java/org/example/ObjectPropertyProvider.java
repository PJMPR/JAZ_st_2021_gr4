package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz) {

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
        Method[] methods = SampleClass.class.getMethods();
        for (Method method : methods) {
            System.out.println("Modifier: " + Modifier.toString(method.getModifiers()));
            System.out.println("Method: " + method.toGenericString());
        }
    }
        class SampleClass {
            private String sampleField;

            public String getSampleField() throws ArrayIndexOutOfBoundsException {
                return sampleField;
            }

            public void setSampleField(String sampleField) {
                this.sampleField = sampleField;
            }
        }




    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
