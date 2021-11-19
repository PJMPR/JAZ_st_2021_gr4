package Errors;

import Handlers.ErrorHandler;
import Handlers.UnsafeMethod;

import java.util.concurrent.TimeoutException;

public class TimeoutExceptionHandler implements ErrorHandler {

    @Override
    public String getMessage() {
        return "Timeout";
    }

    @Override
    public void handle(Exception err, UnsafeMethod method) {
        if (canHandle(err)) {
            System.out.println("Trying to reconnect");
            action.wait(2);
            if (action.redo(method,5)) {
                return;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof TimeoutException;
    }
}
