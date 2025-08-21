package zjg.marketplace.core.image.exception;

public class UnsupportedFileException extends RuntimeException {
    public UnsupportedFileException() {
    }

    public UnsupportedFileException(String message) {
        super(message);
    }

    public UnsupportedFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFileException(Throwable cause) {
        super(cause);
    }

    public UnsupportedFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
