package zjg.marketplace.core.interfaces.services.logging;

public interface ILogger {
    void info(String message);
    <Origin> void info(Class<Origin> origin, String message);
    <Origin, E extends Throwable> void info(Class<Origin> origin, String message, E exception);

    void warning(String message);
    <Origin> void warning(Class<Origin> origin, String message);
    <Origin, TException extends Throwable> void warning(Class<Origin> origin, String message, TException exception);

    void error(String message);
    <Origin> void error(Class<Origin> origin, String message);
    <Origin, TException extends Throwable> void error(Class<Origin> origin, String message, TException exception);
}
