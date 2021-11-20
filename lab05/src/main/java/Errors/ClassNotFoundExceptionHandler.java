package Errors;

import Handlers.ErrorHandler;
import Handlers.UnsafeMethod;

public class ClassNotFoundExceptionHandler implements ErrorHandler {
    @Override
    public String getMessage() {
        return "could not find that class";
    }

    @Override
    public void handle(Exception e, UnsafeMethod method) {
        if (canHandle(e)){
            System.out.println(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof ClassNotFoundException;
    }

}