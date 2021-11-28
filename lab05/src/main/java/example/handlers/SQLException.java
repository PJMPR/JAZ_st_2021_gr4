package example.handlers;


import example.Actions;
import example.Supplier;

public class SQLException implements ExceptionHandler {
    Actions actions = new Actions();

    @Override
    public String getMessage() {
        return "Database is unreachable";
    }

    @Override
    public void handler(Exception error, Supplier method) {
        if(canHandler(error)){
            System.out.println("Cannot connect to database. Reconnecting");
            actions.sleep(5);
            if(actions.restart(method, 5)){
                System.out.println(getMessage());
                logger.log(getMessage());
            }
        }
    }

    @Override
    public boolean canHandler(Exception error) {
        return false;
    }
}