package zjg.marketplace.core.order.strategies.valdiators.exceptions;

public class NullProductsIdException extends OrderException {
    public NullProductsIdException() {
    }

    public NullProductsIdException(String message) {
        super(message);
    }

    public NullProductsIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullProductsIdException(Throwable cause) {
        super(cause);
    }

    public NullProductsIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
