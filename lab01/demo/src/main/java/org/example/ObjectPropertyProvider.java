package org.example;


import javax.management.AttributeList;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public static boolean isGetter(Method method) {
        if(!method.getName().startsWith("get")) {
            if(!method.getName().startsWith("is")) {
                return false;
            }
        }
        if(method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    public static boolean isSetter(Method method) {
        if(!method.getName().startsWith("set")) {
            return false;
        }
        if(method.getParameterTypes().length != 1) {
            return false;
        }
        if(void.class.equals(method.getReturnType())) {
            return true;
        }
        return false;
    }

    public List<Method> getPublicGetters(Class<?> clazz) {

        Method[] methods = clazz.getDeclaredMethods();
        List result = new ArrayList();
        for (Method method : methods) {
            if (Modifier.toString(method.getModifiers()) == "public") {
                if (isGetter(method)) {
                    result.add(method);
                }
            }
        }
        return result;
    }

    public List<Method> getPublicSetters(Class<?> clazz){
          Method[] methods = clazz.getDeclaredMethods();
          List result = new ArrayList();
          for(Method method : methods) {
              if (Modifier.toString(method.getModifiers()) == "public") {
                  if (isSetter(method)) {
                      result.add(method);
                  }
              }
          }
          return result;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz) {

        Field[] fields = clazz.getFields();
        Method[] methods = clazz.getDeclaredMethods();
        List temp = new ArrayList();
        List result = new ArrayList();
        for (Field field : fields) {
            for (Method method : methods) {
                if (method.getName().contains(field.getName())) {
                    temp.add(method);
                }
            }
            if(temp.size()==2) {
                result.add(field);
                temp.clear();
            }
            temp.clear();
        }
        return result;
    }
}
