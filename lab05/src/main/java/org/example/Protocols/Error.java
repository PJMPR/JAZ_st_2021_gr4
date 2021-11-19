package org.example.Protocols;

import org.apache.log4j.Logger;
import org.example.Functions.Functions;

import java.util.concurrent.TimeUnit;

public abstract class Error implements ErrorInterface{
    private Logger logger;

    public Error(Class<?> classArg){
        logger = Logger.getLogger(classArg);
    }

    public void defaultMessage(Exception exception){
        System.out.println(message());
        logger.error(message(), exception);
    }

    public boolean retry(Functions method, int times, int miliSeconds) {
        for (int i = times; i > 0 ; i--) {
            try {
                method.execute();
                return false;
            } catch (Exception exception) {
                wait(TimeUnit.MILLISECONDS, miliSeconds);
            }
        }
        return true;
    }

    public void wait(TimeUnit timeUnit, int amount) {
        try {
            timeUnit.sleep(amount);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}