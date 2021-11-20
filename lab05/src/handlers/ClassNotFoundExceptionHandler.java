package handlers;

import java.io.IOException;

public class ClassNotFoundExceptionHandler extends ExceptionHandler{
    public ClassNotFoundExceptionHandler(Exception exception) {
        super(exception);
    }
    @Override
    public void handle(ActionToCheck action) throws IOException {
        addLog();
    }
    String errormessage = "We cannot find class your are looking for";
}
