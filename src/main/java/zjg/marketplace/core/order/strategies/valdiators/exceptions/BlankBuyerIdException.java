package zjg.marketplace.core.exceptions.validations.order;

public class BlankBuyerIdException extends OrderException {
    public BlankBuyerIdException() {
    }

    public BlankBuyerIdException(String message) {
        super(message);
    }

    public BlankBuyerIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankBuyerIdException(Throwable cause) {
        super(cause);
    }

    public BlankBuyerIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
