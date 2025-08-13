package zjg.marketplace.core.products.strategies.validators.exceptions.title;

public class TitleTooLongException extends TitleException {
    public TitleTooLongException() {
    }

    public TitleTooLongException(String message) {
        super(message);
    }

    public TitleTooLongException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleTooLongException(Throwable cause) {
        super(cause);
    }

    public TitleTooLongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
