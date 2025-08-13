package zjg.marketplace.core.exceptions.security.token;

public class ErrorOnGenerateTokenException extends TokenException {
    public ErrorOnGenerateTokenException() {
    }

    public ErrorOnGenerateTokenException(String message) {
        super(message);
    }

    public ErrorOnGenerateTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorOnGenerateTokenException(Throwable cause) {
        super(cause);
    }

    public ErrorOnGenerateTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
