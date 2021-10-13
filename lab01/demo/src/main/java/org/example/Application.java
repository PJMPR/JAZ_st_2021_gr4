package org.example;

public class Application {

    public static void main(String[] args){

    }
}

class ShopClass {
    private String address;
    private String type;
    private int budget;

    public ShopClass(){
        this.address = "Woronicza 23a";
        this.type = "Odziezowy";
        this.budget = 400000;
    }

    public String getAddress(){
        return this.address;
    }

    public String getType(){
        return this.type;
    }

    public int getBudget(){
        return this.budget;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAddress(int budget){
        this.budget = budget;
    }
}
