package zjg.marketplace.core.product.strategies.validators.exceptions.title;

public abstract class TitleException extends RuntimeException {
    public TitleException() {
    }

    public TitleException(String message) {
        super(message);
    }

    public TitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleException(Throwable cause) {
        super(cause);
    }

    public TitleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
