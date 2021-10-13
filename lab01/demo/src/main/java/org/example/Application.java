package org.example;

import java.lang.reflect.Method;

public class Application {

    public static void main(String[] args){
        ObjectPropertyProvider getsetgo = new ObjectPropertyProvider(); //tworzymy obiekt testujący

        System.out.println("Getters");getsetgo.getPublicGetters(Tester.class);  //wypisuje nam gettery
        System.out.println("Setters");getsetgo.getPublicSetters(Tester.class);  //bedzie wypisywać settery
        //System.out.println("Fields");getsetgo.getFieldsForPublicProperties(Tester.class);  //bedzie wypisywać fieldsy

    }
    public static class Tester{
        private int id;

        public Tester(int tempId){      ///Konstruktor
            id = tempId;
        }

        public int getId(){             ///Getter
            return id;
        }

        public void setName(int newId){ ///Setter
            this.id=newId;
        }
    }
}
