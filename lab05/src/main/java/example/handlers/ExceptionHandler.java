package example.handlers;

import example.Logger;
import example.Supplier;

public interface ExceptionHandler {
    Logger logger = new Logger();
    String getMessage();
    void handler(Exception error, Supplier method);
    boolean canHandler(Exception error);
}
