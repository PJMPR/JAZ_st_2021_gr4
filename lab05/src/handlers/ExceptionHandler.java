package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class ExceptionHandler {
    Path fileName = Path.of("logger.txt");
    public Exception exception;
    String errormessage = "Not handled exception, sorry";
    public void addLog() throws IOException {
        Files.writeString(fileName, errormessage);
    }
    public void addLog(String string) throws IOException {
        Files.writeString(fileName, string);
    }

    public ExceptionHandler(Exception exception) {
        this.exception = exception;
    }
    public void handle(ActionToCheck action) throws IOException {
        addLog(("Not able to handle error named " + exception.getClass().getSimpleName()));
    }

    public boolean didErrorRepeat(Exception exc) {
        return (exc.getClass().getSimpleName().equals(exception.getClass().getSimpleName()));
    }
    public void tryagain(ActionToCheck action, int repeat) throws IOException {
        for (int i = 0; i <= repeat; i++) {
            try {
                action.run();
                break;
            } catch (Exception e) {
                if (didErrorRepeat(e)) {
                    addLog("same error after:" + (i + 1) + " try\\n");
                }
            }
        }
    }

    public void wait(ActionToCheck action,int timeInSeconds) throws IOException {
        try {
            Thread.sleep(timeInSeconds * 1000L);
            action.run();
        } catch (Exception e) {
            if (didErrorRepeat(e)) {
                addLog("same error after waiting longer\\n");
            }
        }

    }

    public void tryAndWait(int timeInSeconds, int repeat,ActionToCheck action) throws IOException {
        for (int i = 0; i <= repeat; i++) {
            try {
                Thread.sleep(timeInSeconds * 1000L);
                action.run();
                break;
            } catch (Exception e) {
                if (didErrorRepeat(e)) {
                    addLog("same error after:" + (i + 1) + " try\\n");
                }
            }
        }
    }
}



