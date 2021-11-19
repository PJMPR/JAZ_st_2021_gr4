package Errors;

import Handlers.ErrorHandler;
import Handlers.UnsafeMethod;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionHandler implements ErrorHandler {
    @Override
    public String getMessage() {
        return "Error, file not found";
    }

    @Override
    public void handle(Exception err, UnsafeMethod method) {

        if(canHandle(err)){
            System.out.println(getMessage());
            logger.log(getMessage());
        }

    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof FileNotFoundException;
    }
}
