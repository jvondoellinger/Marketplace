package zjg.marketplace.core.user.strategies.validators.exceptions.email;

public class EmailFormatException extends EmailException {
    public EmailFormatException() {
    }

    public EmailFormatException(String message) {
        super(message);
    }

    public EmailFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailFormatException(Throwable cause) {
        super(cause);
    }
}
