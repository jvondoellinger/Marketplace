package zjg.marketplace.core.user.auth.exceptions;

public class IncorrectPasswordProvidedException extends FailedToAuthenticateUserException {
    public IncorrectPasswordProvidedException() {
    }

    public IncorrectPasswordProvidedException(String message) {
        super(message);
    }

    public IncorrectPasswordProvidedException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPasswordProvidedException(Throwable cause) {
        super(cause);
    }

    public IncorrectPasswordProvidedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
