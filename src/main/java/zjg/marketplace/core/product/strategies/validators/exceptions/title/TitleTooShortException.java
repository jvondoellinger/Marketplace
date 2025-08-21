package zjg.marketplace.core.product.strategies.validators.exceptions.title;

public class TitleTooShortException extends TitleException {
    public TitleTooShortException() {
    }

    public TitleTooShortException(String message) {
        super(message);
    }

    public TitleTooShortException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleTooShortException(Throwable cause) {
        super(cause);
    }

    public TitleTooShortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
