package zjg.marketplace.core.order.strategies.valdiators.exceptions.path;

public abstract class PathException extends RuntimeException {
    public PathException() {
    }

    public PathException(String message) {
        super(message);
    }

    public PathException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathException(Throwable cause) {
        super(cause);
    }

    public PathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
