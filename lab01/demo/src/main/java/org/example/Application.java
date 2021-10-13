package org.example;

import java.lang.reflect.Method;
import java.util.List;

public class Application {

    public static void main(String[] args){
        ObjectPropertyProvider Obj = new ObjectPropertyProvider();
        List<Method> list = Obj.getPublicGetters(Subject.class);

        for(Method m: list){
            System.out.println(m);
        }
    }
}
