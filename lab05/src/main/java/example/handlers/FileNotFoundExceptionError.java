package example.handlers;

import example.Supplier;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionError implements ExceptionHandler {

    @Override
    public String getMessage() {
        return "File not found";
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
        return error instanceof FileNotFoundException;
    }
}