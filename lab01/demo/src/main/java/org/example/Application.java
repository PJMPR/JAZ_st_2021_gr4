package org.example;
import javax.security.auth.Subject;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String[] args){
        ObjectPropertyProvider ojbproclazz = new ObjectPropertyProvider();
        Method[] metoda = ojbproclazz.getClass().getDeclaredMethods();
        ojbproclazz.getPublicGetters(Subject.class);
        ojbproclazz.getPublicSetters(Subject.class);
    }
}
