package zjg.marketplace.core.product.strategies.validators.exceptions.description;

public class DescriptionTooLongException extends DescriptionException {
    public DescriptionTooLongException() {
    }

    public DescriptionTooLongException(String message) {
        super(message);
    }

    public DescriptionTooLongException(String message, Throwable cause) {
        super(message, cause);
    }

    public DescriptionTooLongException(Throwable cause) {
        super(cause);
    }

    public DescriptionTooLongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
