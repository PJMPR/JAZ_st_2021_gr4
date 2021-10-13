package org.example;

import javax.security.auth.Subject;

public class Application {

    int numero = 0;

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public static void main(String[] args){
        ObjectPropertyProvider opp = new ObjectPropertyProvider();
        opp.getPublicGetters(Subject.class);
    }
}
