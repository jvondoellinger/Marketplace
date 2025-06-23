package zjg.marketplace.core.exceptions.validations.cpf;

public class CpfAllDigitsEqualException extends CpfException {
    public CpfAllDigitsEqualException() {
    }

    public CpfAllDigitsEqualException(String message) {
        super(message);
    }

    public CpfAllDigitsEqualException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfAllDigitsEqualException(Throwable cause) {
        super(cause);
    }
}
