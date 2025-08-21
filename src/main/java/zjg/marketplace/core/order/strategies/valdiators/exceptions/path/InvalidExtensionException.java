package zjg.marketplace.core.order.strategies.valdiators.exceptions.path;

public class InvalidExtensionException extends PathException {
    public InvalidExtensionException() {
    }

    public InvalidExtensionException(String message) {
        super(message);
    }

    public InvalidExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExtensionException(Throwable cause) {
        super(cause);
    }

    public InvalidExtensionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
