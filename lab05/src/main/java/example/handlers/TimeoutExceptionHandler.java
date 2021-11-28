package example.handlers;

import example.Actions;
import example.Supplier;
import java.sql.SQLException;

public class TimeoutExceptionHandler implements ExceptionHandler {

    Actions actions = new Actions();

    @Override
    public String getMessage() {
        return "Connection timed out";
    }

    @Override
    public void handler(Exception error, Supplier method) {
        if(canHandler(error)){
            System.out.println("Connection timed out. Reconnecting");
            actions.sleep(10);
            if(actions.restart(method,5)){
                System.out.println(getMessage());
                logger.log(getMessage());
            }
        }
    }

    @Override
    public boolean canHandler(Exception error) {
        return error instanceof SQLException;
    }
}