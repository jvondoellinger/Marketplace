package zjg.marketplace.core.product.strategies.validators.exceptions.description;

public class DescriptionTooShortException extends DescriptionException {
    public DescriptionTooShortException() {
    }

    public DescriptionTooShortException(String message) {
        super(message);
    }

    public DescriptionTooShortException(String message, Throwable cause) {
        super(message, cause);
    }

    public DescriptionTooShortException(Throwable cause) {
        super(cause);
    }

    public DescriptionTooShortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
