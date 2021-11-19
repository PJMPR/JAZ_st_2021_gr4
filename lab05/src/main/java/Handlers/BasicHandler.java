package Handlers;


public class BasicHandler implements ErrorHandler {

    @Override
    public String getMessage() {
        return "unknown error";
    }

    @Override
    public void handle(Exception err, UnsafeMethod method) {
        System.out.println(getMessage());

    }

    @Override
    public boolean canHandle(Exception err) {
        return true;
    }

}
