package zjg.marketplace.core.exceptions.validations.birthday;

public class UnderageException extends BirthdayException {
    public UnderageException() {
    }

    public UnderageException(String message) {
        super(message);
    }

    public UnderageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnderageException(Throwable cause) {
        super(cause);
    }
}
