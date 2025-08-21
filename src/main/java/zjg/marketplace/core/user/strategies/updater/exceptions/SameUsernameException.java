package zjg.marketplace.core.user.strategies.updater.exceptions;

public class SameUsernameException extends UserUpdaterException {
    public SameUsernameException() {
    }

    public SameUsernameException(String message) {
        super(message);
    }

    public SameUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameUsernameException(Throwable cause) {
        super(cause);
    }

    public SameUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
