package org.example.handlers;

import org.apache.log4j.Logger;
import org.example.suppliers.FunctionSupplier;

import java.util.concurrent.TimeUnit;

public abstract class Responder implements ExceptionHandlerInterface{
    private final Logger logger;

    public Responder(Class<?> classArg){
        logger = Logger.getLogger(classArg);
    }

    public void defaultResponse(Exception exception){
        System.out.println(message());
        logger.error(message(),exception);
    }

    public boolean retry(FunctionSupplier method, int times, int ms) {
        for (int i=times;i>0;i--){
            try{
                method.execute();
                return false;
            } catch (Exception exception) {
                try {
                    TimeUnit.MILLISECONDS.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
