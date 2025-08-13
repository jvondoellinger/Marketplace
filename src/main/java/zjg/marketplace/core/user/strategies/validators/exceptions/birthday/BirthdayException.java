package zjg.marketplace.core.exceptions.validations.birthday;

public abstract class BirthdayException extends RuntimeException {
    public BirthdayException() {
        super();
    }

    public BirthdayException(String message) {
        super(message);
    }

    public BirthdayException(String message, Throwable cause) {
        super(message, cause);
    }

    public BirthdayException(Throwable cause) {
        super(cause);
    }
}
