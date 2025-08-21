package zjg.marketplace.core.user.strategies.validators.exceptions.cpf;

public class CpfLengthInvalidException extends CpfException {
    public CpfLengthInvalidException() {
    }

    public CpfLengthInvalidException(String message) {
        super(message);
    }

    public CpfLengthInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfLengthInvalidException(Throwable cause) {
        super(cause);
    }
}
