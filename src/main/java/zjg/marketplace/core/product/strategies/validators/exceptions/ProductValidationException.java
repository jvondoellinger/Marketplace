package zjg.marketplace.core.product.strategies.validators.exceptions;

public abstract class ProductValidationException extends RuntimeException {
    public ProductValidationException() {
    }

    public ProductValidationException(String message) {
        super(message);
    }

    public ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductValidationException(Throwable cause) {
        super(cause);
    }

    public ProductValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
