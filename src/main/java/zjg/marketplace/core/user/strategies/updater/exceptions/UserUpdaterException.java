package zjg.marketplace.core.user.strategies.updater.exceptions;

public abstract class UserUpdaterException extends RuntimeException{
    public UserUpdaterException() {
    }

    public UserUpdaterException(String message) {
        super(message);
    }

    public UserUpdaterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUpdaterException(Throwable cause) {
        super(cause);
    }

    public UserUpdaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
