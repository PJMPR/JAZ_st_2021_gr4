package org.example.tests;

public class Subject{

    private String Name;
    private int number;
    private boolean isDone;
    private String status;

    private double value;

    public String getStatus() {
        return status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTxt(String txt){
        return txt;
    }
    public void getNothing(){}

    public void setNothing(){}

    public int setInt(int x){
        return x;
    }

    public void emptyMethod(){}
    public int emptyMethodReturn0(){return 0;}
    public void emptyMethodWihParam(int x){}

    private Object something;
    private Object getSomething(){return something;}
    private void setSomething(Object obj){ something = obj;}

}
