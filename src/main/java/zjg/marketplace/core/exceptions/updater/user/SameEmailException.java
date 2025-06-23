package zjg.marketplace.core.exceptions.updater.user;

public class SameEmailException extends UserUpdaterException {
    public SameEmailException() {
    }

    public SameEmailException(String message) {
        super(message);
    }

    public SameEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameEmailException(Throwable cause) {
        super(cause);
    }

    public SameEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
