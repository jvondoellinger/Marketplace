package zjg.marketplace.core.products.strategies.updater.exceptions;

public class SameDescriptionException extends ProductUpdaterException {
    public SameDescriptionException() {
    }

    public SameDescriptionException(String message) {
        super(message);
    }

    public SameDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameDescriptionException(Throwable cause) {
        super(cause);
    }

    public SameDescriptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
