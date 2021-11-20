package handlers;

import java.io.IOException;

public class FileNotFoundExceptionHandler extends ExceptionHandler{
    public FileNotFoundExceptionHandler(Exception exception) {
        super(exception);
    }
    @Override
    public void handle(ActionToCheck action) throws IOException {
        addLog();
        tryagain(action, 2);
    }
    String errormessage = "Please check if given path to file is correct\\n";
}
