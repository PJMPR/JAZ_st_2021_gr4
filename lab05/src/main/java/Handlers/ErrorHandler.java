package Handlers;

public interface ErrorHandler {
    Action action = new Action();
    Logger logger = new Logger();
    String getMessage();
    void handle(Exception err, UnsafeMethod method);

    boolean canHandle(Exception err);
}
