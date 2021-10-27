package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
<<<<<<< HEAD:lab01/demo/src/main/java/org/example/ObjectPropertyProvider.java
        Method[] methods = clazz.getMethods();
        ArrayList methodsToReturn = new ArrayList();

        for(Method method : methods){
            int modifier = method.getModifiers();
            if(Modifier.toString(modifier).equals("public")){
                if(method.getName().startsWith("get") || method.getName().startsWith("is")){
                    if((method.getParameters().length==0)){
                        if(!method.getReturnType().getName().equals("void")){
                            methodsToReturn.add(method);
                        }
                    }
                }
            }
        }
        return methodsToReturn;
=======
        return Arrays.stream(clazz.getDeclaredMethods()).toList();
>>>>>>> parent of ea4c964 (fix):lab01/src/main/java/org/example/ObjectPropertyProvider.java
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        ArrayList methodsToReturn = new ArrayList();

        for(Method method : methods){
            int modifier = method.getModifiers();
            if(Modifier.toString(modifier).equals("public")){
                if(method.getName().startsWith("set")){
                    if((method.getParameters().length==1)){
                        if(method.getReturnType().getName().equals("void")){
                            methodsToReturn.add(method);
                        }
                    }
                }
            }
        }
        return methodsToReturn;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
