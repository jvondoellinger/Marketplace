package zjg.marketplace.core.exceptions.security.user;

public abstract class FailedToAuthenticateUserException extends RuntimeException {
    public FailedToAuthenticateUserException() {
    }

    public FailedToAuthenticateUserException(String message) {
        super(message);
    }

    public FailedToAuthenticateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToAuthenticateUserException(Throwable cause) {
        super(cause);
    }

    public FailedToAuthenticateUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
