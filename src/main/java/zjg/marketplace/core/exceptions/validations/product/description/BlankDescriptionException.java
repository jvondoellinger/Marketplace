package zjg.marketplace.core.exceptions.validations.product.description;

public class BlankDescriptionException extends DescriptionException {
    public BlankDescriptionException() {
    }

    public BlankDescriptionException(String message) {
        super(message);
    }

    public BlankDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankDescriptionException(Throwable cause) {
        super(cause);
    }

    public BlankDescriptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
