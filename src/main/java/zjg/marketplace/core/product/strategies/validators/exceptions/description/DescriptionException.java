package zjg.marketplace.core.products.strategies.validators.exceptions.description;

public abstract class DescriptionException extends RuntimeException {
    public DescriptionException() {
    }

    public DescriptionException(String message) {
        super(message);
    }

    public DescriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DescriptionException(Throwable cause) {
        super(cause);
    }

    public DescriptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
