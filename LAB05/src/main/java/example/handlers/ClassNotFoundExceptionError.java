package example.handlers;

import example.Supplier;

public class ClassNotFoundExceptionError implements ExceptionHandler {

    @Override
    public String getMessage() {
        return "Class not found";
    }

    @Override
    public void handler(Exception error, Supplier method) {
        if(canHandler(error)){
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandler(Exception error) {
        return error instanceof ClassNotFoundException;
    }
}