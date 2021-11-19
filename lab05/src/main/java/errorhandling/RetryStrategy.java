package errorhandling;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import suppliers.Supplier;

import java.util.concurrent.TimeUnit;

public class RetryStrategy implements ErrorHandlingStrategy{
    Supplier method;
    String logMessage;
    int times;
    int milis;

    public RetryStrategy(String logMessage, int times, int milis) {
        this.logMessage = logMessage;
        this.times = times;
        this.milis = milis;
    }


    public Supplier getMethod() {
        return method;
    }

    public void setMethod(Supplier method) {
        this.method = method;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getMilis() {
        return milis;
    }

    public void setMilis(int milis) {
        this.milis = milis;
    }

    @Override
    public void handle() {
        for (int i = 1; i <= times; i++) {
            try {
                method.execute();
                return;
            } catch (Exception e) {
                LogManager.getLogger().log(Level.ERROR, "Attempt %d / %d failed".formatted(i, times));
            }
            try {
                TimeUnit.MILLISECONDS.sleep(milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LogManager.getLogger().log(Level.ERROR, logMessage);
    }
}
