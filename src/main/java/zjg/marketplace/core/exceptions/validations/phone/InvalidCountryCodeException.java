package zjg.marketplace.core.exceptions.validations.phone;

public class InvalidCountryCodeException extends PhoneException {
    public InvalidCountryCodeException() {
    }

    public InvalidCountryCodeException(String message) {
        super(message);
    }

    public InvalidCountryCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCountryCodeException(Throwable cause) {
        super(cause);
    }

    public InvalidCountryCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
