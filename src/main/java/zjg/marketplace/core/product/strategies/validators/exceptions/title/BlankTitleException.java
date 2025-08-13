package zjg.marketplace.core.products.strategies.validators.exceptions.title;

public class BlankTitleException extends TitleException {
    public BlankTitleException() {
    }

    public BlankTitleException(String message) {
        super(message);
    }

    public BlankTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankTitleException(Throwable cause) {
        super(cause);
    }

    public BlankTitleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
