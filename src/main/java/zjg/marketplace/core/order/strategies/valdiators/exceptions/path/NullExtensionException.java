package zjg.marketplace.core.exceptions.validations.product.path;

public class NullExtensionException extends PathException {
    public NullExtensionException() {
    }

    public NullExtensionException(String message) {
        super(message);
    }

    public NullExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullExtensionException(Throwable cause) {
        super(cause);
    }

    public NullExtensionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
