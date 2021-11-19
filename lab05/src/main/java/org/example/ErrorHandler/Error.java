package org.example.ErrorHandler;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public abstract class Error implements ErrorInterface{
    private Logger logger;

    public Error(Class<?> classArg){
        logger = Logger.getLogger(classArg);
    }

    public void defaultAction(Exception exception){
        System.out.println(returnMsg());
        logger.error(returnMsg(), exception);
    }

    public boolean retry(ExecutionProviderInterface method, int times, int millis) {
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