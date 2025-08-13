package zjg.marketplace.core.exceptions.validations.birthday;

public class OverageException extends BirthdayException {
    public OverageException() {
    }

    public OverageException(String message) {
        super(message);
    }

    public OverageException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverageException(Throwable cause) {
        super(cause);
    }
}
