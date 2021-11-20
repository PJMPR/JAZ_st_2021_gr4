package org.example.Exception;

import org.apache.log4j.Logger;
import org.example.Runner;

import java.util.concurrent.TimeUnit;

public abstract class ExceptionModel implements ExceptionInterface {

    private Logger exceptionLogs;

    public ExceptionModel(Class<?> classArg) {
        exceptionLogs = Logger.getLogger(classArg);
    }

    public void defaultResponse(Exception exception) {
        System.out.println(feedback());
        exceptionLogs.error(feedback(), exception);
    }

    public boolean retry(Runner method, int times, int ms) {
        for (int i=times;i>0;i--){
            try {
                method.execute();
                return false;
            } catch (Exception exception) {
                wait(TimeUnit.MILLISECONDS, ms);
            }
        }
        return true;
    }

    public void wait(TimeUnit timeUnit, int number) {
        try {
            timeUnit.sleep(number);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
