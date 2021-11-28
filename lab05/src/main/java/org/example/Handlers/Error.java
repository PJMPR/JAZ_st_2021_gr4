package org.example.Handlers;

import org.apache.log4j.Logger;
import org.example.Suppliers.FunctionSupplier;

import java.util.concurrent.TimeUnit;

public abstract class Error implements ErrorInterface{
    private Logger logger;

    public Error(Class<?> classArg){
        logger = Logger.getLogger(classArg);
    }

    public void defaultResponse(Exception exception){
        System.out.println(returnMessage());
        logger.error(returnMessage(), exception);
    }

    public boolean retry(FunctionSupplier method, int times, int millis) {
        int i = times;
        while ( i>0 ) {
            try {
                method.execute();
                return false;
            } catch (Exception exception) {
                wait(TimeUnit.MILLISECONDS, millis);
            }
            i--;
        }
        return true;
    }

    public void wait(TimeUnit timeUnit, int number) {
        try {
            timeUnit.sleep(number);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}