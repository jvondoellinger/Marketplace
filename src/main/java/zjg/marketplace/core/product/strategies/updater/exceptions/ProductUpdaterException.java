package zjg.marketplace.core.products.strategies.updater.exceptions;

public abstract class ProductUpdaterException extends RuntimeException {
    public ProductUpdaterException() {
    }

    public ProductUpdaterException(String message) {
        super(message);
    }

    public ProductUpdaterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductUpdaterException(Throwable cause) {
        super(cause);
    }

    public ProductUpdaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
