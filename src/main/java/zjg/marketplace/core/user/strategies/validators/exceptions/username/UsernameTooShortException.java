package zjg.marketplace.core.user.strategies.validators.exceptions.username;

public class UsernameTooShortException extends UsernameException {
    public UsernameTooShortException() {
    }

    public UsernameTooShortException(String message) {
        super(message);
    }

    public UsernameTooShortException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameTooShortException(Throwable cause) {
        super(cause);
    }

    public UsernameTooShortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
