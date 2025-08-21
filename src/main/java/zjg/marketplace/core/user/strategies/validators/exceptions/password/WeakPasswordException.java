package zjg.marketplace.core.user.strategies.validators.exceptions.password;

public class WeakPasswordException extends PasswordException {
    public WeakPasswordException() {
    }

    public WeakPasswordException(String message) {
        super(message);
    }

    public WeakPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeakPasswordException(Throwable cause) {
        super(cause);
    }
}
