package zjg.marketplace.core.exceptions.validations.order;

public class NullBuyerIdException extends OrderException {
    public NullBuyerIdException() {
    }

    public NullBuyerIdException(String message) {
        super(message);
    }

    public NullBuyerIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullBuyerIdException(Throwable cause) {
        super(cause);
    }

    public NullBuyerIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
