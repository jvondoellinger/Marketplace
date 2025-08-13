package zjg.marketplace.core.exceptions.validations.cpf;

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
