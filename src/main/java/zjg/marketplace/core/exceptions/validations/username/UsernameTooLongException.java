package zjg.marketplace.core.exceptions.validations.username;

public class UsernameTooLongException extends UsernameException {
    public UsernameTooLongException() {
    }

    public UsernameTooLongException(String message) {
        super(message);
    }

    public UsernameTooLongException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameTooLongException(Throwable cause) {
        super(cause);
    }

    public UsernameTooLongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
