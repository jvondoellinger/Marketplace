package zjg.marketplace.core.exceptions.validations.phone;

public class InvalidAreaCodeException extends PhoneException {
    public InvalidAreaCodeException() {
    }

    public InvalidAreaCodeException(String message) {
        super(message);
    }

    public InvalidAreaCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAreaCodeException(Throwable cause) {
        super(cause);
    }

    public InvalidAreaCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
