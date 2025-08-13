package zjg.marketplace.core.exceptions.security.user;

public class EmailNotExistsException extends FailedToAuthenticateUserException {
    public EmailNotExistsException() {
    }

    public EmailNotExistsException(String message) {
        super(message);
    }

    public EmailNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotExistsException(Throwable cause) {
        super(cause);
    }

    public EmailNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
