package zjg.marketplace.core.exceptions.validations.product.title;

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
