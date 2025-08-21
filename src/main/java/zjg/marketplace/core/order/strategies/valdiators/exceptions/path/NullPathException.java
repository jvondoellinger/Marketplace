package zjg.marketplace.core.order.strategies.valdiators.exceptions.path;

public class NullPathException extends PathException {
    public NullPathException() {
    }

    public NullPathException(String message) {
        super(message);
    }

    public NullPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPathException(Throwable cause) {
        super(cause);
    }

    public NullPathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
