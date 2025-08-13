package zjg.marketplace.core.exceptions.validations.email;

public abstract class EmailException extends RuntimeException {
    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }
}
