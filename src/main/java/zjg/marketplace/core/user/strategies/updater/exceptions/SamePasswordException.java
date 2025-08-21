package zjg.marketplace.core.user.strategies.updater.exceptions;

public class SamePasswordException extends UserUpdaterException {
    public SamePasswordException() {
    }

    public SamePasswordException(String message) {
        super(message);
    }

    public SamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamePasswordException(Throwable cause) {
        super(cause);
    }

    public SamePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
