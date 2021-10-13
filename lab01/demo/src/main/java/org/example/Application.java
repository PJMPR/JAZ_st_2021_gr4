package org.example;

public class Application {

    public static void main(String[] args){
        ObjectPropertyProvider obj = new ObjectPropertyProvider();
        obj.getPublicGetters(Subject.class);
    }
}
