package zjg.marketplace.core.user.strategies.validators.exceptions.username;

public class BlankUsernameException extends UsernameException {
    public BlankUsernameException() {
    }

    public BlankUsernameException(String message) {
        super(message);
    }

    public BlankUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankUsernameException(Throwable cause) {
        super(cause);
    }

    public BlankUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
