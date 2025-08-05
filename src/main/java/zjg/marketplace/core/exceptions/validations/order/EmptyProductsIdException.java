package zjg.marketplace.core.exceptions.validations.order;

public class EmptyProductsIdException extends OrderException {
    public EmptyProductsIdException() {
    }

    public EmptyProductsIdException(String message) {
        super(message);
    }

    public EmptyProductsIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyProductsIdException(Throwable cause) {
        super(cause);
    }

    public EmptyProductsIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
