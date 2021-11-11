package org.example;

import org.example.caching.Cache;

public class App {

    public static void main(String[] args){


    }



}
class SafeCaster{

    public <T,E> T cast(E obj, Class<T> clazz){

        T result = null;
        try{
            if(obj != null) result = clazz.cast(obj);
        return result;
        }

            catch (ClassCastException ex){
                //ex.printStackTrace();
            }
        return null;
    }

}