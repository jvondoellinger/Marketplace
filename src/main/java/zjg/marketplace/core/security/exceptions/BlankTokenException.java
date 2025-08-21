package zjg.marketplace.core.security.exceptions;

public class BlankTokenException extends TokenException {
    public BlankTokenException() {
    }

    public BlankTokenException(String message) {
        super(message);
    }

    public BlankTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankTokenException(Throwable cause) {
        super(cause);
    }

    public BlankTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
