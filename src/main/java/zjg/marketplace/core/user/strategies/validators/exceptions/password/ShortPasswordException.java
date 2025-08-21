package zjg.marketplace.core.user.strategies.validators.exceptions.password;

public class ShortPasswordException extends PasswordException {
    public ShortPasswordException() {
    }

    public ShortPasswordException(String message) {
        super(message);
    }

    public ShortPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortPasswordException(Throwable cause) {
        super(cause);
    }
}
