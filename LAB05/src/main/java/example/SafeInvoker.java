package example;

import example.handlers.*;
import example.handlers.ClassNotFoundExceptionError;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    DefaultException defaultErrorHandler = new DefaultException();

    List<ExceptionHandler> exceptionHandlerList = new ArrayList<>(List.of(
            new ClassNotFoundExceptionError(),
            new FileNotFoundExceptionError(),
            new SQLException(),
            new TimeoutExceptionHandler()
    ));

    public void invoke(Supplier method){
        try{
            method.execute();
        } catch (Exception e) {
            AtomicBoolean wasHandled = new AtomicBoolean(false);
            exceptionHandlerList.stream()
                    .filter(errorHandler -> errorHandler
                            .canHandler(e))
                    .forEach(errorHandler -> {
                        errorHandler
                                .handler(e,method);
                        wasHandled.set(true);
                    });
            if(!wasHandled.get()){
                defaultErrorHandler
                        .handler(e,method);
            }
        }
    }
}