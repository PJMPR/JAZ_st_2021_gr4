package Errors;

import Handlers.ErrorHandler;
import Handlers.UnsafeMethod;

import java.sql.SQLException;

public class SQLExceptionHandler implements ErrorHandler {
    @Override
    public String getMessage() {
        return "Database connection error";
    }

    @Override
    public void handle(Exception err, UnsafeMethod method) {
        if (canHandle(err)) {

            System.out.println("Trying to reconnect");
            action.wait(2);

            if (action.redo(method, 5)) {
                return;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }


    @Override
    public boolean canHandle(Exception err) {
        return err instanceof SQLException;
    }
}
