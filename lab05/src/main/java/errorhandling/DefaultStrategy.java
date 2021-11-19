package errorhandling;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class DefaultStrategy implements ErrorHandlingStrategy {
    Exception exception;

    public DefaultStrategy(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void handle(){
        LogManager.getLogger().log(Level.ERROR, "An unknown error has occurred, this was the stack: ");
    }
}
