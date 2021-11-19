package example.handlers;

import example.Supplier;

public class DefaultException implements ExceptionHandler {

    @Override
    public String getMessage() {
        return "Error";
    }

    @Override
    public void handler(Exception error, Supplier method) {
        System.out.println(getMessage());
        logger.log(getMessage());
    }

    @Override
    public boolean canHandler(Exception error) {
        return true;
    }
}
