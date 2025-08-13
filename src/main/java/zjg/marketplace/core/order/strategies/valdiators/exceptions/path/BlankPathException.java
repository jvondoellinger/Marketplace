package zjg.marketplace.core.exceptions.validations.product.path;

public class BlankPathException extends PathException {
    public BlankPathException() {
    }

    public BlankPathException(String message) {
        super(message);
    }

    public BlankPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankPathException(Throwable cause) {
        super(cause);
    }

    public BlankPathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
