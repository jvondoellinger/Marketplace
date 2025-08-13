package zjg.marketplace.core.user.exceptions;

public class SamePhoneNumberException extends UserUpdaterException {
    public SamePhoneNumberException() {
    }

    public SamePhoneNumberException(String message) {
        super(message);
    }

    public SamePhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamePhoneNumberException(Throwable cause) {
        super(cause);
    }

    public SamePhoneNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
