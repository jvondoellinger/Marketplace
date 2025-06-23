package zjg.marketplace.core.exceptions.updater.product;

public class SameTitleException extends ProductUpdaterException {
    public SameTitleException() {
    }

    public SameTitleException(String message) {
        super(message);
    }

    public SameTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameTitleException(Throwable cause) {
        super(cause);
    }

    public SameTitleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
